package com.converter.api.infra.dataBase.model

import com.converter.api.mocks.ConversionMocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ConversionModelTest {
    @Test
    fun `given an model, when convert to entity, should transfer all properties`() {
        // given
        val model = ConversionMocks.model()

        // when
        val result = model.toEntity()

        // assert
        assertEquals(model.id, result.id)
        assertEquals(model.userId, result.userId)
        assertEquals(model.originCurrency, result.originCurrency)
        assertEquals(model.originValue, result.originValue)
        assertEquals(model.destinyCurrency, result.destinyCurrency)
        assertEquals(model.conversionRate, result.conversionRate)
        assertEquals(model.transactionTime, result.transactionTime)
    }

    @Test
    fun `given an entity, when convert to model, should transfer all properties`() {
        // given
        val entity = ConversionMocks.entity()

        // when
        val result = entity.toModel()

        // assert
        assertEquals(entity.id, result.id)
        assertEquals(entity.userId, result.userId)
        assertEquals(entity.originCurrency, result.originCurrency)
        assertEquals(entity.originValue, result.originValue)
        assertEquals(entity.destinyCurrency, result.destinyCurrency)
        assertEquals(entity.conversionRate, result.conversionRate)
        assertEquals(entity.transactionTime, result.transactionTime)
    }
}
