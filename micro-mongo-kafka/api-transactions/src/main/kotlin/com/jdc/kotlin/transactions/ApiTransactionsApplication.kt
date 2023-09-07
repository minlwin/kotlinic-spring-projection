package com.jdc.kotlin.transactions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiTransactionsApplication

fun main(args: Array<String>) {
    runApplication<ApiTransactionsApplication>(*args)
}
