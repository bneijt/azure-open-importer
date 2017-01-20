package nl.bigdatarepublic.importer.open.nl.knmi.elements

import org.joda.time.DateTime

case class HourlyMeasurements(date : DateTime, measurements : List[HourlyMeasurement])
