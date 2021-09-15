package com.converter.api

import com.converter.api.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class ConverterApplication {

    @Bean
    @Profile("dev")
    fun generateDevUser(userService: UserService): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            userService.create(
                email = "email@email.com",
                password = "12345678"
            )
        }
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ConverterApplication::class.java, *args)
        }
    }

}