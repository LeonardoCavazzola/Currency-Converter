package com.converter.api.domain.entity

import java.math.BigDecimal

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
