package compass

import cats.effect.IO
import compass.services.providers.SeriesProvider
import org.http4s._
import org.http4s.implicits._

class SeriesProviderpec extends munit.FunSuite {

  test("SeriesProvider returns 200") {
    assert(retSeries.status == Status.Ok)
  }
  test("returns Game of Thrones") {
    assert(retSeries.as[String].unsafeRunSync() == "[{\"name\":\"Game of Thrones\"}]")
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
