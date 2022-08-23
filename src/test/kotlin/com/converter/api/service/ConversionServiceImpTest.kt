package com.converter.api.service

import com.converter.api.domain.gateway.RatesClient
import com.converter.api.domain.repository.ConversionRepository
import com.converter.api.mocks.ConversionIntentMocks
import com.converter.api.mocks.ConversionMocks
import com.converter.api.mocks.RatesMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder

@ExtendWith(MockKExtension::class)
internal class ConversionServiceImpTest {

    @MockK
    lateinit var conversionRepository: ConversionRepository

    @MockK
    lateinit var ratesClient: RatesClient

    @InjectMockKs
    lateinit var conversionService: ConversionServiceImp

    @Test
    fun `given a userId, when get MyConversions, should return a page with all user's conversions`() {
        // given
        val conversion = ConversionMocks.entity()
        val userId = conversion.userId
        val pageable: Pageable = Pageable.ofSize(10)
        val page = PageImpl(listOf(conversion), pageable, 1)

        mockkStatic(SecurityContextHolder::class)
        every { SecurityContextHolder.getContext()?.authentication?.principal } returns userId

        every { conversionRepository.findAllByUserId(userId, pageable) } returns page

        // when
        val result = conversionService.getMyConversions(pageable)

        //assert
        assertEquals(1, result.totalElements)
        assertEquals(conversion, result.content[0])
    }

    @Test
    fun `given a ConversionIntent, when convert, should return a Conversion`() {
        // given
        val intent = ConversionIntentMocks.entity()
        val conversion = ConversionMocks.entity()
        val rates = RatesMocks.entity()

        every { conversionRepository.save(any()) } returns conversion

        every { ratesClient.getRates(any(), any()) } returns rates

        // when
        val result = conversionService.convert(intent)

        //assert
        assertEquals(conversion, result)
    }
}