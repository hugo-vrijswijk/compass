package compass

import cats.effect.{Concurrent, ConcurrentEffect, Timer}
import fs2.Stream
import org.http4s.HttpApp
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import scala.concurrent.ExecutionContext.global

object CompassServer {
  def stream[F[_]: ConcurrentEffect: Concurrent](
      implicit T: Timer[F]
  ): Stream[F, Nothing] = {
    for {
      client <- BlazeClientBuilder[F](global).stream

      jokeAlg = Jokes.impl[F](client)

      services            = CompassRoutes.jokeRoutes[F](jokeAlg)
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
