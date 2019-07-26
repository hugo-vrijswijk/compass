package compass.services.providers

import cats.Applicative
import cats.implicits._
import compass.model.Api.Series

trait SeriesProvider[F[_]] {
  def all(): F[Seq[Series]]
  def one(name: String): F[Series]
}

object SeriesProvider {
  implicit def apply[F[_]](implicit ev: SeriesProvider[F]): SeriesProvider[F] = ev

  def impl[F[_]: Applicative]: SeriesProvider[F] = new SeriesProvider[F] {
    override def all(): F[Seq[Series]] = Seq(Series("Game of Thrones")).pure[F]

    override def one(name: String): F[Series] = Series("Game of Thrones").pure[F]
  }

  /**
    * More generally you will want to decouple your edge representations from
    * your internal data structures, however this shows how you can
    * create encoders for your data.
    **/
  final case class Greeting(greeting: String) extends AnyVal

}
