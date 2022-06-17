package com.converter.api.infra.repository

import com.converter.api.infra.model.ConversionModel
import com.converter.api.infra.model.toModel
import com.converter.api.mocks.ConversionMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

@ExtendWith(MockKExtension::class)
internal class ConversionRepositoryImpTest {

    @MockK
    lateinit var modelRepository: ConversionModelRepository

    @InjectMockKs
    lateinit var userRepositoryImp: ConversionRepositoryImp

    @Test
    fun `given a conversion, when save, should save and return the conversion`() {
        // given
        val conversion = ConversionMocks.entity()
        val model = conversion.toModel()

        every { modelRepository.save(any()) } returns model

        // when
        val result = userRepositoryImp.save(conversion)

        // assert
        assertEquals(conversion, result)
    }

    @Test
    fun `given an user of exiting conversions, when findAllByUser, should return a page of conversions`() {
        // given
        val conversion = ConversionMocks.entity()
        val user = conversion.user
        val pageable = Pageable.ofSize(10)
        val modelPage = PageImpl(listOf(conversion.toModel()))

        every { modelRepository.findAllByUser(any(), any()) } returns modelPage

        // when
        val result = userRepositoryImp.findAllByUser(user, pageable)

        // assert
        assertEquals(conversion, result.content[0])
    }

    @Test
    fun `given an user of not exiting conversions, when findAllByUser, should return an empty page`() {
        // given
        val conversion = ConversionMocks.entity()
        val user = conversion.user
        val pageable = Pageable.ofSize(10)
        val modelPage = Page.empty<ConversionModel>()

        every { modelRepository.findAllByUser(any(), any()) } returns modelPage

        // when
        val result = userRepositoryImp.findAllByUser(user, pageable)

        // assert
        assertEquals(0, result.size)
    }
}
