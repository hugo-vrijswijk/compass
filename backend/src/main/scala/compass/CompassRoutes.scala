package compass

import cats.effect.Sync
import cats.implicits._
import compass.services.providers.SeriesProvider
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

object CompassRoutes {

  def jokeRoutes[F[_]: Sync](J: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- J.get
          resp <- Ok(joke)
        } yield resp
    }
  }

  def seriesProviderRoutes[F[_]: Sync](S: SeriesProvider[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    import compass.model.Encoder._
    import io.circe.generic.auto._

    HttpRoutes.of[F] {
      case GET -> Root / "series" / name =>
        for {
          series <- S.one(name)
          resp   <- Ok(series)
        } yield resp
      case GET -> Root / "series" =>
        for {
          series <- S.all()
          resp   <- Ok(series)
        } yield resp
    }
  }

  def providerRouter[F[_]: Sync](S: SeriesProvider[F]): HttpRoutes[F] =
    Router("/provider" -> seriesProviderRoutes(S))
}
