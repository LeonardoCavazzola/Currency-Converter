package com.converter.api.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class UserTest {

    @Test //é pra falhar mesmo
    fun mustEncryptThePassword() {
        val user = User(
            email = "a@a.com",
            password = "12345678"
        )
        Assertions.assertNotEquals("12345678", user.password)
    }
}