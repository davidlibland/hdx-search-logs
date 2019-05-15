package org.humandx.search.logs

import com.madewithtea.mockedstreams.MockedStreams
import com.madewithtea.mockedstreams.MockedStreams.Builder
import org.apache.kafka.common.serialization.Serdes
import org.scalatest._


/**
  * Test cases for the Kafka Streams sample transformation.
  */
class AggregatorExampleSpec extends FlatSpec with Matchers {
  import Serdes._

  val input: Seq[(String, String)] = Seq(
    "hello there",
    "I love kafka streams",
    "kafka streams is the greatest",
    "the best way to stream is kafka"
  ).map(s => ("", s))
  val expStreamHead: Seq[(String, Long)] = Seq(
    ("hello", 1L),
    ("there", 1L),
    ("i", 1L),
    ("love", 1L)
  )
  val expTable: Map[String, Long] = Map(
    "is" -> 2,
    "best" -> 1,
    "way" -> 1,
    "i" -> 1,
    "to" -> 1,
    "there" -> 1,
    "kafka" -> 3,
    "streams" -> 2,
    "greatest" -> 1,
    "stream" -> 1,
    "love" -> 1,
    "hello" -> 1,
    "the" -> 2
  )

  val mstreams: Builder = MockedStreams()
    .withTopology(Aggregator.createTopology)
    .input("TextLinesTopic", String, String, input)

  mstreams.output("WordsWithCountsTopic", String, Long, 20).toMap shouldEqual expTable

  mstreams.output("WordsWithCountsTopic", String, Long, expStreamHead.size) shouldEqual expStreamHead
}