package com.converter.api.infra.dataBase.repository

import com.converter.api.domain.repository.ConversionRepository
import com.converter.api.mocks.ConversionMocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable

@SpringBootTest
internal class ConversionRepositoryImpIntegratedTest {

    @Autowired
    lateinit var conversionRepository: ConversionRepository

    @Test
    fun `given a conversion, when save, should save and return the conversion`() {
        // given
        val conversion = ConversionMocks.entity()

        // when
        val result = conversionRepository.save(conversion)

        // assert
        assertEquals(result, conversionRepository.findById(conversion.id!!))
    }

    @Test
    fun `given an id of an exiting conversions, when findById, should return a conversion`() {
        // given
        val conversion = ConversionMocks.entity()
        val id = conversion.id!!

        conversionRepository.save(conversion)

        // when
        val result = conversionRepository.findById(id)

        // assert
        assertEquals(conversion, result)
    }

    @Test
    fun `given an user of exiting conversions, when findAllByUser, should return a page of conversions`() {
        // given
        val conversion = ConversionMocks.entity()
        val userId = conversion.userId
        val pageable = Pageable.ofSize(10)

        conversionRepository.save(conversion)

        // when
        val result = conversionRepository.findAllByUserId(userId, pageable)

        // assert
        assertEquals(conversion, result.content[0])
    }
}
