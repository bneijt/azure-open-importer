package nl.bigdatarepublic.importer.open.nl.knmi

import scala.io.Source

class KnmiSpec extends org.specs2.mutable.Specification {
  val source = Source.fromURL(getClass.getResource("/nl/bigdatarepublic/importer/open/nl/knmi/20170101.txt")).mkString

  "KNMI should parse first line of example download" >> {
    Knmi.hourlyMeasurementsAsObjects(source).head.STN must beEqualTo("391")
  }

}
