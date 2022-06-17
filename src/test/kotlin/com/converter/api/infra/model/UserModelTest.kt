package com.converter.api.infra.model

import com.converter.api.mocks.UserMocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserModelTest {
    @Test
    fun `given an model, when convert to entity, should transfer all properties`() {
        // given
        val model = UserMocks.model()

        // when
        val result = model.toEntity()

        // assert
        assertEquals(model.id, result.id)
        assertEquals(model.email, result.email)
        assertEquals(model.password, result.password)
    }

    @Test
    fun `given an entity, when convert to model, should transfer all properties`() {
        // given
        val entity = UserMocks.entity()

        // when
        val result = entity.toModel()

        // assert
        assertEquals(entity.id, result.id)
        assertEquals(entity.email, result.email)
        assertEquals(entity.password, result.password)
    }
}

