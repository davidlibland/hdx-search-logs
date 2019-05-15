import sbt._

object Dependencies {
  // Core Libraries:
  lazy val kafkaStreams = "org.apache.kafka" %% "kafka-streams-scala" % "2.2.0"
  // Logging:
  lazy val sl4jApi = "org.slf4j" % "slf4j-api" % "1.7.21"
  lazy val sl4jSimple = "org.slf4j" % "slf4j-simple" % "1.7.21"
  // HDx Libraries:
  lazy val scalapb = "org.humandx" % "scalapb" % "2.11.0"
  // Testing:
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val mockedStreams = "com.madewithtea" %% "mockedstreams" % "3.3.0"
}
