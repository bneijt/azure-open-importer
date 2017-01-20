package nl.bigdatarepublic.importer.open.nl.knmi

import akka.NotUsed
import com.mashape.unirest.http.Unirest
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

import scala.io.Source



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

  def hourlyMeasurementsAsAvro(contents: String): String = {
    //Create AVRO binary blob from contents
    contents
  }
}
