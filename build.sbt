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
      // Core libraries:
      kafkaStreams,
      // Logging:
      sl4jApi,
      sl4jSimple,
      // HDx:
      scalapb,
      // Testing:
      scalaTest % Test,
      mockedStreams % Test
    )
  )
