package com.converter.api.controller.dto

import com.converter.api.mocks.ConversionMocks
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ConversionOutputTest {

    @Test
    fun `given a Conversion, when convert to output, should transfer all properties`() {
        // given
        val conversion = ConversionMocks.entity()

        // when
        val result = conversion.toOutput()

        // assert
        assertEquals(conversion.id, result.id)
        assertEquals(conversion.userId, result.userId)
        assertEquals(conversion.originCurrency, result.originCurrency)
        assertEquals(conversion.originValue, result.originValue)
        assertEquals(conversion.destinyCurrency, result.destinyCurrency)
        assertEquals(conversion.destinyValue, result.destinyValue)
        assertEquals(conversion.conversionRate, result.conversionRate)
        assertEquals(conversion.transactionTime, result.transactionTime)
    }
}