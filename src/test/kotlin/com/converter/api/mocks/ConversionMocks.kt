package com.converter.api.mocks

import com.converter.api.domain.entity.Conversion
import com.converter.api.infra.model.ConversionModel
import java.math.BigDecimal
import java.time.ZonedDateTime

object ConversionMocks {
    fun entity() = Conversion(
        id = 1,
        user = UserMocks.entity(),
        originCur = "BRL",
        originValue = BigDecimal.TEN,
        destinyCur = "USD",
        conversionRate = BigDecimal("1.5565465461"),
        transactionTime = ZonedDateTime.now()
    )

    fun model() = ConversionModel(
        id = 1,
        user = UserMocks.model(),
        originCur = "BRL",
        originValue = BigDecimal.TEN,
        destinyCur = "USD",
        conversionRate = BigDecimal("1.5565465461"),
        transactionTime = ZonedDateTime.now()
    )
}
