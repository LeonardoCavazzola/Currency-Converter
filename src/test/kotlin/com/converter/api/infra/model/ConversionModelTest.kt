package com.converter.api.infra.model

import com.converter.api.mocks.ConversionMocks
import org.junit.jupiter.api.Assertions.*
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
        assertEquals(model.user.toEntity(), result.user)
        assertEquals(model.originCur, result.originCur)
        assertEquals(model.originValue, result.originValue)
        assertEquals(model.destinyCur, result.destinyCur)
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
        assertEquals(entity.user.toModel(), result.user)
        assertEquals(entity.originCur, result.originCur)
        assertEquals(entity.originValue, result.originValue)
        assertEquals(entity.destinyCur, result.destinyCur)
        assertEquals(entity.conversionRate, result.conversionRate)
        assertEquals(entity.transactionTime, result.transactionTime)
    }
}
