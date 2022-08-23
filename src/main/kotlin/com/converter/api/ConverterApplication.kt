package com.converter.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ConverterApplication

fun main(args: Array<String>) {
    SpringApplication.run(ConverterApplication::class.java, *args)
}
