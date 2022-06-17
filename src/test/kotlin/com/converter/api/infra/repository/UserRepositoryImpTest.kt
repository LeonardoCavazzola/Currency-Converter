package com.converter.api.infra.repository

import com.converter.api.infra.model.toEntity
import com.converter.api.mocks.UserMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class UserRepositoryImpTest {

    @MockK
    lateinit var modelRepository: UserModelRepository

    @InjectMockKs
    lateinit var userRepositoryImp: UserRepositoryImp

    @Test
    fun `given an email of exiting user, when findByEmail, should return the user`() {
        // given
        val model = UserMocks.model()
        val email = model.email

        every { modelRepository.findByEmail(email) } returns model

        // when
        val result = userRepositoryImp.findByEmail(email)

        // assert
        assertEquals(model.toEntity(), result)
    }

    @Test
    fun `given an email of not exiting user, when findByEmail, should return null`() {
        // given
        val email = "what ever"

        every { modelRepository.findByEmail(email) } returns null

        // when
        val result = userRepositoryImp.findByEmail(email)

        // assert
        assertNull(result)
    }
}

