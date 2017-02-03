package nl.bigdatarepublic.importer.open.nl.knmi

import java.io.{File, StringReader}

import akka.NotUsed
import com.mashape.unirest.http.Unirest
import com.sksamuel.avro4s.AvroOutputStream
import nl.bigdatarepublic.importer.open.nl.knmi.elements.{HourlyMeasurement, HourlyMeasurements}
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
  
  def toAvroFile(basePath : String, hourlyMeasurements: HourlyMeasurements): String = {
    val outputFilename = new File(basePath, dateFormatter.print(hourlyMeasurements.date) + ".avro").toString
    val os = AvroOutputStream.data[HourlyMeasurement](new File(outputFilename))
    try {
      os.write(hourlyMeasurements.measurements)
    } finally {
      os.close()
    }
    outputFilename
  }

  def downloadHourlyMeasurements(downloadDate: DateTime): Tuple2[DateTime, String]  = {
    val dateTimeString = dateFormatter.print(downloadDate)
    Tuple2(downloadDate, Unirest.post("http://projects.knmi.nl/klimatologie/uurgegevens/getdata_uur.cgi")
      .field("stns", "ALL")
      .field("start", dateTimeString)
      .field("end", dateTimeString)
      .asString().getBody())
  }

  def intOf(value : String): Int = {
    if (value.trim.isEmpty) null.asInstanceOf[Int] else value.trim.toInt
  }

  def booleanOf(value: String) : Boolean = {
    if (value == null || value.trim.isEmpty) {
      return null.asInstanceOf[Boolean]
    }
    if("0".equals(value)) return false
    if("1".equals(value)) return true
    value.trim.toBoolean
  }

  def hourlyMeasurementsAsObjects(t: Tuple2[DateTime, String]): HourlyMeasurements = {
    val downloadDate: DateTime = t._1
    val contents = t._2
    //Create AVRO binary blob from contents
    val reader = new CsvListReader(new StringReader(contents), CsvPreference.STANDARD_PREFERENCE)

    try {
      HourlyMeasurements(
        downloadDate,
        Iterator.continually(reader.read)
          .takeWhile(_ != null)
          .map(_.asScala.toList)
          .filterNot(_.size < 1)
          .filterNot(_.head.startsWith("#")) //Filter comments
          .filterNot(_.size < 24)
          //        .map(_.map(x => if(x == null) "" else x))
          .map(_.map(x => if (x != null) x.trim else x))
          .map(line => HourlyMeasurement(
            line(0),
            intOf(line(1)),
            intOf(line(2)),
              intOf(line(3)),
            intOf(line(4)),
            intOf(line(5)),
              intOf(line(6)),
            intOf(line(7)),
            intOf(line(8)),
            intOf(line(9)),
            intOf(line(10)),
            intOf(line(11)),
            intOf(line(12)),
            intOf(line(13)),
            intOf(line(14)),
            intOf(line(15)),
            intOf(line(16)),
            intOf(line(17)),
            intOf(line(18)),
              intOf(line(19)),
            booleanOf(line(20)),
            booleanOf(line(21)),
              booleanOf(line(22)),
                booleanOf(line(23)),
                  booleanOf(line(24))
          )).toList
      )
    } finally {
      reader.close()
    }
  }
}
