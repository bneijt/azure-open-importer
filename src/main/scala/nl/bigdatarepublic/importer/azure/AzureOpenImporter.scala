package nl.bigdatarepublic.importer.azure

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ThrottleMode}
import akka.stream.scaladsl.Source
import nl.bigdatarepublic.importer.DateSource
import nl.bigdatarepublic.importer.open.nl.knmi.Knmi
import org.joda.time.DateTime

import scala.concurrent.Future
import scala.concurrent.duration._


object AzureOpenImporter extends App {

  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  val dateSource = new DateSource(DateTime.now().minusDays(2))
  val source: Source[DateTime, NotUsed] = Source[DateTime](dateSource)
//  source.runForeach(i => println(i))

    val knmi: Future[Done] = source
      .throttle(1, 1.second, 1, ThrottleMode.shaping)
      .map(Knmi.downloadHourlyMeasurements)
        .map(Knmi.hourlyMeasurementsAsAvro)
      .runForeach(println)
  (materializer)
}
