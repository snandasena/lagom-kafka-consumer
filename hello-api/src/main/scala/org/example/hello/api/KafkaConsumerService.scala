package org.example.hello.api

import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service}

object KafkaConsumerService {
  val topicName = "analytics-events"
}

trait KafkaConsumerService extends Service {

  def consumeEvents(): Topic[String]

  override def descriptor: Descriptor = {
    import Service._
    named("kafka-consumer")
      .withTopics(topic(KafkaConsumerService.topicName, consumeEvents _))
      .withAutoAcl(true)
  }
}
