package com.jdc.spring.hellokafka.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class TopicConfig {

    @Bean
    fun firstTopic() = TopicBuilder.name("firstTopic").build()
}