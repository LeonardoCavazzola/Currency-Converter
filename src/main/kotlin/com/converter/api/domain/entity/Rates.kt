package com.converter.api.domain.entity

import java.math.BigDecimal

data class Rates(
    val success: Boolean,
    val base: String,
    val rates: Map<String, BigDecimal>
)
