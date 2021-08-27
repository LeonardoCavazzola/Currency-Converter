package com.converter.api.service;

import com.converter.api.dto.RatesResponse;
import com.converter.api.model.Conversion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("dev")
class ConversionServiceTest {

    @Autowired
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRates() {
        RatesResponse rates = conversionService.getRates("BRL", "USD", "EUR");
        Assertions.assertTrue(rates.success());
        Assertions.assertNotNull(rates.rates().get("BRL"));
        Assertions.assertNotNull(rates.rates().get("USD"));
        Assertions.assertNotNull(rates.rates().get("EUR"));
    }

    @Test
    void convert() {
        Conversion convert = conversionService.convert("BRL", "USD", new BigDecimal("5"));
        Assertions.assertTrue(convert.getConversionRate().compareTo(BigDecimal.ZERO) > 0 );
        Assertions.assertTrue(convert.getOriginValue().compareTo(BigDecimal.ZERO) > 0 );
        Assertions.assertNotNull(convert.getOriginCur());
        Assertions.assertNotNull(convert.getDestinyCur());
        Assertions.assertNotNull(convert.getTransactionTime());
    }
}
