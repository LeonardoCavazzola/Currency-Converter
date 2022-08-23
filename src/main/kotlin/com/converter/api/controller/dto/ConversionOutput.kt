package com.converter.api.controller.dto

import com.converter.api.domain.entity.Conversion
import java.math.BigDecimal
import java.time.ZonedDateTime

data class ConversionOutput(
    val id: Long?,
    val userId: String,
    val originCurrency: String,
    val originValue: BigDecimal,
    val destinyCurrency: String,
    val destinyValue: BigDecimal,
    val conversionRate: BigDecimal,
    val transactionTime: ZonedDateTime
)

fun Conversion.toOutput() = ConversionOutput(
    id = id,
    userId = userId,
    originCurrency = originCurrency,
    originValue = originValue,
    destinyCurrency = destinyCurrency,
    destinyValue = destinyValue,
    conversionRate = conversionRate,
    transactionTime = transactionTime,
)
