package com.converter.api.mocks

import com.converter.api.domain.entity.Rates
import com.converter.api.infra.gateway.model.RatesResponse
import java.math.BigDecimal

object RatesMocks {
    fun entity() = Rates(
        success = true,
        base = "EUR",
        rates = mapOf(
            "BRL" to BigDecimal("5"),
            "USD" to BigDecimal("2"),
        ),
    )

    fun model() = RatesResponse(
        success = true,
        base = "EUR",
        rates = mapOf(
            "BRL" to BigDecimal("5"),
            "USD" to BigDecimal("2"),
        ),
    )
}
