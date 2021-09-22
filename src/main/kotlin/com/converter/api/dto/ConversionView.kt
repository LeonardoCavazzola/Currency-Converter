package com.converter.api.dto

import org.springframework.hateoas.RepresentationModel
import com.converter.api.model.Conversion
import java.math.BigDecimal
import java.time.LocalDateTime

class ConversionView(
    val id: Long?,
    val userId: Long?,
    val originCurrency: String,
    val originValue: BigDecimal,
    val destinyCurrency: String,
    val destinyValue: BigDecimal,
    val conversionRate: BigDecimal,
    val transactionTime: LocalDateTime
) : RepresentationModel<ConversionView?>() {


    constructor(conversion: Conversion) : this(
        conversion.id,
        conversion.user.id,
        conversion.originCur,
        conversion.originValue,
        conversion.destinyCur,
        conversion.getDestinyValue(),
        conversion.conversionRate,
        conversion.transactionTime,
    )
}