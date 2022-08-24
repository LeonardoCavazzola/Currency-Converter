package com.converter.api.mocks

import com.converter.api.controller.dto.ConversionIntentInput
import com.converter.api.domain.entity.ConversionIntent
import java.math.BigDecimal

object ConversionIntentMocks {
    fun entity() = ConversionIntent(
        originCurrency = "BRL",
        originValue = BigDecimal.TEN,
        destinyCurrency = "USD",
    )

    fun input() = ConversionIntentInput(
        originCurrency = "BRL",
        originValue = BigDecimal.TEN,
        destinyCurrency = "USD",
    )
}
