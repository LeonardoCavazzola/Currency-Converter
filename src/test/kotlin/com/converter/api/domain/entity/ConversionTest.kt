package com.converter.api.domain.entity

import com.converter.api.mocks.ConversionIntentMocks
import com.converter.api.mocks.ConversionMocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.MathContext

internal class ConversionTest {
    @Test
    fun `given a conversion, when get destinyValue, should return the value`() {
        // given
        val conversion = ConversionMocks.entity()

        // when
        val result = conversion.destinyValue

        // assert
        assertEquals(BigDecimal("15.565470000000"), result)
    }

    @Test
    fun `given a conversionIntent, originRate and a destinyRate, when fromIntent, should return a Conversion`() {
        // given
        val intent = ConversionIntentMocks.entity()
        val originRate = BigDecimal("2")
        val destinyRate = BigDecimal("9")

        // when
        val result = Conversion.fromIntent(intent, originRate, destinyRate)

        // assert
        assertEquals(intent.originCurrency, result.originCurrency)
        assertEquals(intent.originValue, result.originValue)
        assertEquals(intent.destinyCurrency, result.destinyCurrency)
        assertEquals(destinyRate.divide(originRate, MathContext.DECIMAL128), result.conversionRate)
    }
}
