package com.jdc.kotlin.dao

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommonDataAccessApplication

fun main(args: Array<String>) {
    runApplication<CommonDataAccessApplication>(*args)
}
