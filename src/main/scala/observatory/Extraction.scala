package observatory

import java.time.LocalDate

/**
  * 1st milestone: data extraction
  */
object Extraction {

  /**
    * @param year             Year number
    * @param stationsFile     Path of the stations resource file to use (e.g. "/stations.csv")
    * @param temperaturesFile Path of the temperatures resource file to use (e.g. "/1975.csv")
    * @return A sequence containing triplets (date, location, temperature)
    */
  def locateTemperatures(year: Year, stationsFile: String, temperaturesFile: String): Iterable[(LocalDate, Location, Temperature)] = {
    //    ???
    val statStrings = ofFile(stationsFile).filter(_.length > 2)
    val stations: Map[String, Location] = statStrings.toSeq.groupBy(s => s(0) + "_" + s(1)).mapValues(l => Location(l.head(2).toDouble, l.head(3).toDouble))
    //  010010,,01,01,23.2
    //  010010,,01,20,16.5
    val tempStrings = ofFile(temperaturesFile).filter(_.length > 3)
    val emptyLocation = Location(99999999.99, 99999999.99)
    tempStrings
      .map(t => {
        (LocalDate.of(year, t(2).toInt, t(3).toInt), stations.getOrElse(t(0) + "_" + t(1), emptyLocation), (t(4).toDouble - 32) / 1.8)
      })
      .filter(_._2 != emptyLocation)
      //T(°C) = (T(°F) - 32) / 1.8
      .toIterable
  }

  private def ofFile(stationsFile: String): Iterator[Array[String]] = {
    //    val stream0 = getClass.getResourceAsStream(stationsFile)
    //    println(stationsFile)
    //    println (scala.io.Source.fromInputStream(stream0).getLines().mkString("\n"))
    val stream = getClass.getResourceAsStream(stationsFile)
    scala.io.Source.fromInputStream(stream).getLines().map(_.split(','))
  }

  /**
    * @param records A sequence containing triplets (date, location, temperature)
    * @return A sequence containing, for each location, the average temperature over the year.
    */
  def locationYearlyAverageRecords(records: Iterable[(LocalDate, Location, Temperature)]): Iterable[(Location, Temperature)] = {
    //    ???
    records
      .groupBy(_._2)
      .mapValues(_.map { case (_, _, temp) => temp })
      .mapValues { rec => rec.foldLeft(0.0) { (acc, cur) => acc + cur } / rec.size }
  }

}
