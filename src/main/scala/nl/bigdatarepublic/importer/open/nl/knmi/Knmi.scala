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



  def toAvroFile(hourlyMeasurements: HourlyMeasurements): String = {
    val outputFilename = dateFormatter.print(hourlyMeasurements.date) + ".avro"
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
      )
    } finally {
      reader.close()
    }
  }
}
