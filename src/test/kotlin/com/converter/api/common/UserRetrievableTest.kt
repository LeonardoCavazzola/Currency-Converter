package com.converter.api.common

import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.security.core.context.SecurityContextHolder

internal class UserRetrievableTest {

    private val obj: UserRetrievable = object : UserRetrievable {}

    @Test
    fun `given a authenticated context, when get authenticatedUserId, should return an user id`() {
        // given
        val userId = "1"

        mockkStatic(SecurityContextHolder::class)
        every { SecurityContextHolder.getContext().authentication.principal } returns userId

        // when
        val result = obj.authenticatedUserId

        // assert
        assertEquals(userId, result)
    }

    @Test
    fun `given an unauthenticated context, when get authenticatedUserId, should return an user id`() {
        // given
        mockkStatic(SecurityContextHolder::class)
        every { SecurityContextHolder.getContext().authentication.principal } returns null

        // when
        val result = obj.authenticatedUserId

        // assert
        assertEquals("Anonymous user", result)
    }
}
