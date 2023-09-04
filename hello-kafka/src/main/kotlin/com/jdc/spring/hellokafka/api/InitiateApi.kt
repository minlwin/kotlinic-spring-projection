package com.jdc.spring.hellokafka.api

import com.jdc.spring.hellokafka.model.ApiResponse
import com.jdc.spring.hellokafka.model.Data
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("initiate")
class InitiateApi(private val template: KafkaTemplate<String, Data>) {

    @PostMapping
    fun initiate(@RequestBody data:Data):ApiResponse {
        println(data)
        template.send("firstTopic", data)
        return ApiResponse("Received Message is : ${data.value}")
    }
}