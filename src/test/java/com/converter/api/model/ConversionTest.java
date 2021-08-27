package com.converter.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class ConversionTest {

    @Test
    void getDestinyValue() {
        Conversion conversion = new Conversion(new User(), "BRL", new BigDecimal("5"), "USD", new BigDecimal("3"));
        Assertions.assertEquals(new BigDecimal("15"), conversion.getDestinyValue());
    }
}
