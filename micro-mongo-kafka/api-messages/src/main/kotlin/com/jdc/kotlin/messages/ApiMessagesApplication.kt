package com.jdc.kotlin.messages

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiMessagesApplication

fun main(args: Array<String>) {
    runApplication<ApiMessagesApplication>(*args)
}
