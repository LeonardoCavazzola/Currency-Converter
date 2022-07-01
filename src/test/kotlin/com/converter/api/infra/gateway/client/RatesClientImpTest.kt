package com.converter.api.infra.gateway.client

import com.converter.api.infra.gateway.model.RatesResponse
import com.converter.api.mocks.RatesMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@ExtendWith(MockKExtension::class)
internal class RatesClientImpTest {

    @MockK
    lateinit var client: RestTemplate

    val key: String = "whatever"

    @InjectMockKs
    lateinit var ratesClientImp: RatesClientImp

    @Test
    fun `given two currencies, when getRates, should return the rates`() {
        // given
        val model = RatesMocks.model()
        val originCur = "USD"
        val destinyCur = "BRL"

        every {
            client.exchange(any(), HttpMethod.GET, null, RatesResponse::class.java, any())
        } returns ResponseEntity.ok(model)

        // when
        val result = ratesClientImp.getRates(originCur, destinyCur)

        // assert
        assertEquals(2, result.rates.size)
    }
}
