import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.4.16"
  lazy val jodaTime = "joda-time" % "joda-time" % "2.9.7"
  lazy val uniRest = "com.mashape.unirest" % "unirest-java" % "1.4.9"
  lazy val superCSV = "net.sf.supercsv" % "super-csv-joda" % "2.4.0"
  lazy val spec2 = "org.specs2" %% "specs2-core" % "3.8.7"
  lazy val avro2s = "com.sksamuel.avro4s" %% "avro4s-core" % "1.6.4"
  lazy val azureStorage = "com.microsoft.azure" % "azure-storage" % "4.0.0"

}
