package com.converter.api.domain.entity

import com.converter.api.mocks.ConversionMocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ConversionTest {
    @Test
    fun `given a conversion, when get destinyValue, should return the value`() {
        // given
        val conversion = ConversionMocks.entity()

        // when
        val result = conversion.destinyValue

        // assert
        assertEquals(BigDecimal("15.5654654610"), result)
    }
}
