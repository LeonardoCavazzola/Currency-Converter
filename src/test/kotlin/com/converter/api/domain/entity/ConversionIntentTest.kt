package com.converter.api.domain.entity

import com.converter.api.mocks.ConversionIntentMocks
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.security.core.context.SecurityContextHolder

internal class ConversionIntentTest {

    @Test
    fun `given a conversion intent, when convert, should return a complete conversion`() {
        // given
        val intent = ConversionIntentMocks.entity()
        val origin = 5.toBigDecimal()
        val destiny = 10.toBigDecimal()
        val userId = "1"

        mockkStatic(SecurityContextHolder::class)
        every { SecurityContextHolder.getContext().authentication.principal } returns userId

        // when
        val result = intent.convert(origin, destiny)

        // assert
        assertEquals(userId, result.userId)
        assertEquals(intent.originValue, result.originValue)
        assertEquals(intent.originCurrency, result.originCurrency)
        assertEquals(intent.destinyCurrency, result.destinyCurrency)
        assertEquals(destiny / origin, result.conversionRate)
        assertEquals(intent.destinyCurrency, result.destinyCurrency)
    }
}
