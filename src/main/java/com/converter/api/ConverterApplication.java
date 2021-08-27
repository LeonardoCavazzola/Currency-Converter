package com.converter.api;

import com.converter.api.model.User;
import com.converter.api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner generateDevUser(UserService userService){
        return args -> userService.create(new User("email@email.com", "12345678"));
    }
}
