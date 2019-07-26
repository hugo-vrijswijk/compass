package compass.model

import cats.Applicative
import org.http4s.EntityEncoder

object Api {

  final case class Series(name: String) extends AnyVal

}

object Encoder {
  implicit def genericEncoder[F[_]: Applicative, G](implicit encoder: Encoder[G]): EntityEncoder[F, G] = {
    import io.circe.generic.auto._
    import org.http4s.circe.jsonEncoderOf
    jsonEncoderOf[F, G]
  }
}