package com.converter.api.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ConversionTest {

    @Test
    fun destinyValue() {
        val user = User(
            email = "a@a.com",
            password = "pass"
        )
        val conversion = Conversion(
            user = user,
            originCur = "BRL",
            originValue = BigDecimal("5"),
            destinyCur = "USD",
            conversionRate = BigDecimal("3")
        )
        Assertions.assertEquals(BigDecimal("15"), conversion.getDestinyValue())
    }
}