package com.converter.api.infra.gateway.model

import com.converter.api.mocks.RatesMocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RatesResponseTest {
    @Test
    fun `given an model, when convert to entity, should transfer all properties`() {
        // given
        val model = RatesMocks.model()

        // when
        val result = model.toEntity()

        // assert
        assertEquals(model.success, result.success)
        assertEquals(model.base, result.base)
        assertEquals(model.rates, result.rates)
    }
}
