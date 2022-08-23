package com.converter.api.infra.dataBase.model

import com.converter.api.domain.entity.Conversion
import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ConversionModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) val userId: String,
    @Column(nullable = false) val originCurrency: String,
    @Column(nullable = false, scale = 6, precision = 18) val originValue: BigDecimal,
    @Column(nullable = false) val destinyCurrency: String,
    @Column(nullable = false, scale = 6, precision = 18) val conversionRate: BigDecimal,
    @Column(nullable = false) val transactionTime: ZonedDateTime,
)

fun ConversionModel.toEntity() = Conversion(
    id = id,
    userId = userId,
    originCurrency = originCurrency,
    originValue = originValue,
    destinyCurrency = destinyCurrency,
    conversionRate = conversionRate,
    transactionTime = transactionTime,
)

fun Conversion.toModel() = ConversionModel(
    id = id,
    userId = userId,
    originCurrency = originCurrency,
    originValue = originValue,
    destinyCurrency = destinyCurrency,
    conversionRate = conversionRate,
    transactionTime = transactionTime,
)
