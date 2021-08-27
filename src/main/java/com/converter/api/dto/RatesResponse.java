package com.converter.api.dto;

import java.math.BigDecimal;
import java.util.Map;

public record RatesResponse(
        Boolean success,
        String base,
        Map<String, BigDecimal> rates) {
}
