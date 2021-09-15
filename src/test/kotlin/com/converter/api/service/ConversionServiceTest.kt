package com.converter.api.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@SpringBootTest
@ActiveProfiles("dev")
internal class ConversionServiceTest {

    @Autowired
    private val conversionService: ConversionService? = null

    @Test
    fun getRates() {
        val rates = conversionService!!.getRates("BRL", "USD", "EUR")
        Assertions.assertTrue(rates.success)
        Assertions.assertNotNull(rates.rates["BRL"])
        Assertions.assertNotNull(rates.rates["USD"])
        Assertions.assertNotNull(rates.rates["EUR"])
    }

    @Test
    fun convert() {
        val (_, _, originCur, originValue, destinyCur, conversionRate, transactionTime) = conversionService!!.convert(
            "BRL",
            "USD",
            BigDecimal("5")
        )
        Assertions.assertTrue(conversionRate > BigDecimal.ZERO)
        Assertions.assertTrue(originValue > BigDecimal.ZERO)
        Assertions.assertNotNull(originCur)
        Assertions.assertNotNull(destinyCur)
        Assertions.assertNotNull(transactionTime)
    }
}