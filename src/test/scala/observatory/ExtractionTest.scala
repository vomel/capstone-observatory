package observatory

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

trait ExtractionTest extends FunSuite {

//  @RunWith(classOf[JUnitRunner])
  class TestCheck extends FunSuite {
    test ("secret test goes well"){
      val tuples = Extraction.locateTemperatures(2000,"/stations2.csv","/temperatures2.csv")
      assert(tuples == List.empty)
    }
  }

}