package com.converter.api.infra.dataBase.repository

import com.converter.api.infra.dataBase.model.toEntity
import com.converter.api.mocks.UserMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
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
        val email = "whatever"

        every { modelRepository.findByEmail(email) } returns null

        // when
        val result = userRepositoryImp.findByEmail(email)

        // assert
        assertNull(result)
    }

    @Test
    fun `given an user, when save, should save and return the user`() {
        // given
        val user = UserMocks.entity()
        val model = UserMocks.model()

        every { modelRepository.save(model) } returns model

        // when
        val result = userRepositoryImp.save(user)

        // assert
        assertEquals(user, result)
    }

    @Test
    fun `given an email of exiting user, when existsByEmail, should return the true`() {
        // given
        val email = "whatever"

        every { modelRepository.existsByEmail(email) } returns true

        // when
        val result = userRepositoryImp.existsByEmail(email)

        // assert
        assertTrue(result)
    }

    @Test
    fun `given an email of not exiting user, when existsByEmail, should return false`() {
        // given
        val email = "whatever"

        every { modelRepository.existsByEmail(email) } returns false

        // when
        val result = userRepositoryImp.existsByEmail(email)

        // assert
        assertFalse(result)
    }
}

