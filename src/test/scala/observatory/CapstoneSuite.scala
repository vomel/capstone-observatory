package observatory

import java.time.LocalDate

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CapstoneSuite
  extends ExtractionTest
    with VisualizationTest
    with InteractionTest
    with ManipulationTest
    with Visualization2Test
    with Interaction2Test {
  test("secret test goes well") {
    val expected = List(
      (LocalDate.of(2000, 1, 1), Location(1.0, -1.0), 10.0),
      (LocalDate.of(2000, 1, 2), Location(2.0, -2.0), 10.0),
      (LocalDate.of(2000, 1, 3), Location(3.0, -3.0), 10.0),
      (LocalDate.of(2000, 1, 4), Location(4.0, -4.0), 10.0),
      (LocalDate.of(2000, 1, 5), Location(5.0, -5.0), 10.0)
    )
    val tuples = Extraction.locateTemperatures(2000, "/stations2.csv", "/temperatures2.csv").toList
    assert(tuples == expected,s"\n$tuples\n$expected")
  }

}
