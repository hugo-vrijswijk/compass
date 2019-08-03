package compass

import cats.effect.IO
import compass.services.providers.SeriesProvider
import org.http4s._
import org.http4s.implicits._
import minitest._

object HelloWorldSpec extends SimpleTestSuite {

  test("HelloWorld return 200") {
    assertEquals(retHelloWorld.status, Status.Ok)
  }
  test("return hello world") {
    assertEquals(
      retHelloWorld.as[String].unsafeRunSync(),
      "{\"message\":\"Hello, world\"}"
    )
  }

  private[this] val retHelloWorld: Response[IO] = {
    val getHW      = Request[IO](Method.GET, uri"/hello/world")
    val helloWorld = SeriesProvider.impl[IO]
    CompassRoutes
      .seriesProviderRoutes(helloWorld)
      .orNotFound(getHW)
      .unsafeRunSync()
  }

}
