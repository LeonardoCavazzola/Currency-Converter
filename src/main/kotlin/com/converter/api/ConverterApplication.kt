package com.converter.api

import com.converter.api.model.User
import com.converter.api.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication
class ConverterApplication {

    @Bean
    @Profile("dev")
    fun generateDevUser(userService: UserService): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            userService.create(
                User(
                    email = "email@email.com",
                    password = "12345678"
                )
            )
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ConverterApplication::class.java, *args)
        }
    }
}