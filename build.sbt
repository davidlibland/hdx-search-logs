import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.humandx",
      scalaVersion := "2.12.8",
      version      := "0.1"
    )),
    resolvers += Resolver.mavenLocal,
    name := "hdx-search-logs",
    libraryDependencies ++= Seq(
      kafkaStreams,
      sl4jApi,
      sl4jSimple,
      scalapb,
      //      scalaTest % Test
    )
  )
