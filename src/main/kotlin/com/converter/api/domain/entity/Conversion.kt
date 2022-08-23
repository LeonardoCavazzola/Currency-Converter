package com.converter.api.domain.entity

import com.converter.api.common.UserRetrievable
import java.math.BigDecimal
import java.math.MathContext
import java.time.ZoneId
import java.time.ZonedDateTime

class Conversion(
    val id: Long? = null,
    val userId: String,
    val originCurrency: String,
    val originValue: BigDecimal,
    val destinyCurrency: String,
    val conversionRate: BigDecimal,
    val transactionTime: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
) {
    val destinyValue: BigDecimal
        get() = originValue.multiply(conversionRate, MathContext.UNLIMITED)

    companion object : UserRetrievable {
        fun fromIntent(
            conversionIntent: ConversionIntent,
            originRate: BigDecimal,
            destinyRate: BigDecimal,
        ) = Conversion(
            userId = authenticatedUserId,
            originCurrency = conversionIntent.originCurrency,
            originValue = conversionIntent.originValue,
            destinyCurrency = conversionIntent.destinyCurrency,
            conversionRate = destinyRate.divide(originRate, MathContext.DECIMAL128)
        )
    }
}
