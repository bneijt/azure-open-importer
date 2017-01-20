package nl.bigdatarepublic.importer.open.nl.knmi

import java.io.StringReader

import akka.NotUsed
import com.mashape.unirest.http.Unirest
import nl.bigdatarepublic.importer.open.nl.knmi.elements.HourlyMeasurement
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.supercsv.io.CsvListReader
import org.supercsv.prefs.CsvPreference

import scala.io.Source
import scala.collection.immutable.List
import scala.util.Try
import scala.collection.JavaConverters._

object Knmi {
  val dateFormatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd")

  def downloadHourlyMeasurements(downloadDate: DateTime): String = {
    val dateTimeString = dateFormatter.print(downloadDate)
    Unirest.post("http://projects.knmi.nl/klimatologie/uurgegevens/getdata_uur.cgi")
      .field("stns", "ALL")
      .field("start", dateTimeString)
      .field("end", dateTimeString)
      .asString().getBody()
  }

  def hourlyMeasurementsAsObjects(contents: String): List[HourlyMeasurement] = {
    //Create AVRO binary blob from contents
    val reader = new CsvListReader(new StringReader(contents), CsvPreference.STANDARD_PREFERENCE)
    val x = null :String;
    val b = if(x == null) x else "";

    try {
      Iterator.continually(reader.read)
        .takeWhile(_ != null)
        .map(_.asScala.toList)
        .filterNot(_.size < 1)
        .filterNot(_.head.startsWith("#")) //Filter comments
        .filterNot(_.size < 24)
        .map(_.map(x => if(x == null) "" else x))
        .map(_.map(_.trim))
        .map(line => HourlyMeasurement(
          line(0),
          line(1),
          line(2),
          line(3),
          line(4),
          line(5),
          line(6),
          line(7),
          line(8),
          line(9),
          line(10),
          line(11),
          line(12),
          line(13),
          line(14),
          line(15),
          line(16),
          line(17),
          line(18),
          line(19),
          line(20),
          line(21),
          line(22),
          line(23),
          line(24)
        )).toList
    } finally {
      reader.close()
    }
  }
}
