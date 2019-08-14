package compass

import cats.effect.IO
import compass.services.providers.SeriesProvider
import org.http4s._
import org.http4s.implicits._
import minitest._

object SeriesProviderpec extends SimpleTestSuite {

  test("SeriesProvider returns 200") {
    assertEquals(retSeries.status, Status.Ok)
  }
  test("returns Game of Thrones") {
    assertEquals(
      retSeries.as[String].unsafeRunSync(),
      "[{\"name\":\"Game of Thrones\"}]"
    )
  }

  private[this] val retSeries: Response[IO] = {
    val getSeries = Request[IO](Method.GET, uri"/series")
    val series    = SeriesProvider.impl[IO]
    CompassRoutes
      .seriesProviderRoutes(series)
      .orNotFound(getSeries)
      .unsafeRunSync()
  }

}
