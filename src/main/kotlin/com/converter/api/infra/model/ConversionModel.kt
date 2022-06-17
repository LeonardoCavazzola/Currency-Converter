package com.converter.api.infra.model

import com.converter.api.domain.entity.Conversion
import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class ConversionModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne val user: UserModel,
    @Column(nullable = false) val originCur: String,
    @Column(nullable = false, scale = 6, precision = 18) val originValue: BigDecimal,
    @Column(nullable = false) val destinyCur: String,
    @Column(nullable = false, scale = 6, precision = 18) val conversionRate: BigDecimal,
    @Column(nullable = false) val transactionTime: ZonedDateTime,
)

fun ConversionModel.toEntity() = Conversion(
    id = id,
    user = user.toEntity(),
    originCur = originCur,
    originValue = originValue,
    destinyCur = destinyCur,
    conversionRate = conversionRate,
    transactionTime = transactionTime,
)

fun Conversion.toModel() = ConversionModel(
    id = id,
    user = user.toModel(),
    originCur = originCur,
    originValue = originValue,
    destinyCur = destinyCur,
    conversionRate = conversionRate,
    transactionTime = transactionTime,
)
