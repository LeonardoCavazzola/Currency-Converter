package com.converter.api.domain.entity

import java.math.BigDecimal
import java.math.MathContext

class ConversionIntent(
    val originCurrency: String,
    val originValue: BigDecimal,
    val destinyCurrency: String,
) {
    fun convert(originRate: BigDecimal, destinyRate: BigDecimal): Conversion = Conversion.fromIntent(
        conversionIntent = this,
        originRate = originRate,
        destinyRate = destinyRate,
    )
}

fun Conversion.Companion.fromIntent(
    conversionIntent: ConversionIntent,
    originRate: BigDecimal,
    destinyRate: BigDecimal,
) = Conversion(
    user = Conversion.authenticatedUser,
    originCurrency = conversionIntent.originCurrency,
    originValue = conversionIntent.originValue,
    destinyCurrency = conversionIntent.destinyCurrency,
    conversionRate = destinyRate.divide(originRate, MathContext.UNLIMITED)
)
