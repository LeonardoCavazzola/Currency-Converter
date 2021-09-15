package com.converter.api.dto

import org.springframework.hateoas.RepresentationModel
import com.converter.api.model.Conversion
import java.math.BigDecimal
import java.time.LocalDateTime

class ConversionView(conversion: Conversion) : RepresentationModel<ConversionView?>() {
    private val id: Long?
    private val userId: Long?
    private val originCurrency: String
    private val originValue: BigDecimal
    private val destinyCurrency: String
    private val destinyValue: BigDecimal
    private val conversionRate: BigDecimal
    private val transactionTime: LocalDateTime

    init { // TODO: 14/09/2021 isso é uma boa pratica?
        id = conversion.id
        userId = conversion.user.id
        originCurrency = conversion.originCur
        originValue = conversion.originValue
        destinyCurrency = conversion.destinyCur
        destinyValue = conversion.getDestinyValue()
        conversionRate = conversion.conversionRate
        transactionTime = conversion.transactionTime
    }
}