package com.converter.api.dto

import java.math.BigDecimal

data class RatesResponse(
    val success: Boolean,
    val base: String,
    val rates: Map<String, BigDecimal>
)