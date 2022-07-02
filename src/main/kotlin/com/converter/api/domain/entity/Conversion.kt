package com.converter.api.domain.entity

import com.converter.api.common.UserRetrievable
import java.math.BigDecimal
import java.math.MathContext
import java.time.ZoneId
import java.time.ZonedDateTime

data class Conversion(
    val id: Long? = null,
    val user: User,
    val originCurrency: String,
    val originValue: BigDecimal,
    val destinyCurrency: String,
    val conversionRate: BigDecimal,
    val transactionTime: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
) {
    val destinyValue: BigDecimal
        get() = originValue.multiply(conversionRate, MathContext.UNLIMITED)

    companion object : UserRetrievable
}
