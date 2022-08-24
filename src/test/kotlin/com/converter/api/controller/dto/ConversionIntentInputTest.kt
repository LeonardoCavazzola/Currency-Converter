package com.converter.api.controller.dto

import com.converter.api.mocks.ConversionIntentMocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ConversionIntentInputTest{
    @Test
    fun `given a ConversionIntent, when convert to entity, should transfer all properties`() {
        // given
        val conversion = ConversionIntentMocks.input()

        // when
        val result = conversion.toEntity()

        // assert
        assertEquals(conversion.originCurrency, result.originCurrency)
        assertEquals(conversion.originValue, result.originValue)
        assertEquals(conversion.destinyCurrency, result.destinyCurrency)
    }
}