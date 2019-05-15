package org.humandx.search.logs

import java.time.Duration
import java.util.Properties

import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}


object Aggregator extends App {
  import Serdes._

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "hdx-search-log-aggregator")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, sys.env("KAFKA_URL")+ ":9092")
    p
  }

  run()

  def createTopology(): Topology = {
    val builder: StreamsBuilder = new StreamsBuilder
    val textLines: KStream[String, String] = builder.stream[String, String]("TextLinesTopic")
    val wordCounts: KTable[String, Long] = textLines
      .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
      .groupBy((_, word) => word)
      .count()
    wordCounts.toStream.to("WordsWithCountsTopic")

    builder.build()
  }

  private def run(): Unit = {
    val topology = createTopology()
    val streams: KafkaStreams = new KafkaStreams(topology, props)
    streams.start()
    sys.ShutdownHookThread {
      streams.close(Duration.ofSeconds(10))
    }
  }
}