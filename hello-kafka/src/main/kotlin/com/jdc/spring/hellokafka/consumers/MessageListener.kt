package com.jdc.spring.hellokafka.consumers

import com.jdc.spring.hellokafka.model.Data
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageListener {

    private val logger = LoggerFactory.getLogger(MessageListener::class.java)

    @KafkaListener(groupId = "firstGroup", topics = ["firstTopic"])
    fun consume(message:Data) {
        logger.info("KAFKA CONSUMES -> $message")
    }
}