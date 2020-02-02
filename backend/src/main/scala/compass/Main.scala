package compass

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    CompassServer.stream[IO].compile.drain.as(ExitCode.Success)
}
