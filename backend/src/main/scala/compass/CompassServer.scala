package compass

import cats.MonoidK
import cats.effect.{Concurrent, ConcurrentEffect, ContextShift, Timer}
import cats.implicits._
import compass.services.providers.SeriesProvider
import fs2.Stream
import org.http4s.HttpApp
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder

import scala.concurrent.ExecutionContext.global

object CompassServer {
  def stream[F[_]: ConcurrentEffect: Concurrent](implicit T: Timer[F], C: ContextShift[F]): Stream[F, Nothing] = {
    for {
      client <- BlazeClientBuilder[F](global).stream

      jokeAlg           = Jokes.impl[F](client)
      seriesProviderAlg = SeriesProvider.impl[F]

      services = CompassRoutes.jokeRoutes[F](jokeAlg) <+>
        CompassRoutes.providerRouter(seriesProviderAlg)
      httpApp: HttpApp[F] = Router("/api" -> services).orNotFound

      // With Middlewares in place
//      finalHttpApp = Middleware.all(httpApp)

      exitCode <- BlazeServerBuilder[F]
        .bindHttp(8080, "0.0.0.0")
        .withHttpApp(httpApp)
        .serve
    } yield exitCode
  }.drain
}
