package compass.model

import cats.Applicative
import org.http4s.EntityEncoder
import io.circe.{Encoder => CirceEncoder}
object Api {

  final case class Series(name: String) extends AnyVal

}

object Encoder {
  implicit def genericEncoder[F[_]: Applicative, G](
      implicit encoder: CirceEncoder[G]
  ): EntityEncoder[F, G] = {
    import org.http4s.circe.jsonEncoderOf
    jsonEncoderOf[F, G]
  }
}
