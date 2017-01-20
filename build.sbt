import Dependencies._




lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "nl.bigdatarepublic",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "azure-open-importer",
    libraryDependencies ++= List(
      akkaStream,
      jodaTime,
      uniRest,
      superCSV,
      avro2s,
      scalaTest % Test,
      spec2 % Test)
  )
