package observatory

import java.time.LocalDate

/**
  * @author vomel
  * @since 11/18/17 21:36
  */
object AppTest extends App {

    val a = Extraction.locateTemperatures(
      1975,
      "/stations.csv",
      "/1975.csv"
    )
    private val list: List[(LocalDate, Location, Double)] = a.toList
    println(list.size)
  //    * @return A sequence containing, for each location, the average temperature over the year.

  val b = Seq(
    (LocalDate.of(1975, 1, 29), Location(-0.0, +0.0), 20.0),
    (LocalDate.of(1975, 5, 29), Location(-0.0, +0.0), 10.0)
  )
  //  val bb: Seq[(LocalDate, Location, Double, Int)] = b.map{case (a,b,c) => (a,b,c,1)}
  //  bb.foldLeft((0.0,0))((temp,c)=>)
  //val numbers = List(5, 4, 8, 6, 2)
  //  //accumulated value and the current item in the list
  // val res = b.map(_._3).fold(0.0) { (z:Double, i:Double) =>
  //    z + i
  //  }
  //  println (res/b.size)
  private val tuples: Iterable[(Location, Double)] = Extraction.locationYearlyAverageRecords(b)
  println(tuples)
}

