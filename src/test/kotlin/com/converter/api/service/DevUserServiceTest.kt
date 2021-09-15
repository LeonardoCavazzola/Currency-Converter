package com.converter.api.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("dev")
internal class DevUserServiceTest {
    @Autowired
    private val devUserService: DevUserService? = null

    @Test
    fun getLoggedUser() {
        val (id) = devUserService!!.getLoggedUser()
        Assertions.assertEquals(java.lang.Long.valueOf("1"), id)
    }
}