package compass

import cats.MonoidK
import cats.effect.Concurrent
import org.http4s.HttpApp
import org.http4s.server.middleware.{AutoSlash, Logger}
object Middleware {
  type Middleware[F[_]] = HttpApp[F] => HttpApp[F]
  def all[F[_]: Concurrent: MonoidK]: HttpApp[F] => HttpApp[F] = {
    Logger.httpApp(true, true)((_: HttpApp[F]))
  } andThen {
    AutoSlash(_)
  }
}
