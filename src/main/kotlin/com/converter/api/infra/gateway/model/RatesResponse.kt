package com.converter.api.infra.gateway.model

import com.converter.api.domain.entity.Rates
import java.math.BigDecimal

data class RatesResponse(
    val success: Boolean,
    val base: String,
    val rates: Map<String, BigDecimal>
)

fun RatesResponse.toEntity() = Rates(
    success = success,
    base = base,
    rates = rates,
)
