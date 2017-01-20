package nl.bigdatarepublic.importer.open.nl.knmi

import org.joda.time.DateTime

import scala.io.Source

class KnmiSpec extends org.specs2.mutable.Specification {
  val source = Source.fromURL(getClass.getResource("/nl/bigdatarepublic/importer/open/nl/knmi/20170101.txt")).mkString

  "KNMI should parse first line of example download" >> {
    Knmi.hourlyMeasurementsAsObjects(Tuple2(DateTime.now(), source)).measurements.head.STN must beEqualTo("391")
  }

}
