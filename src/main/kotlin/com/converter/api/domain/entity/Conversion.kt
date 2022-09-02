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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Conversion

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (originCurrency != other.originCurrency) return false
        if (originValue != other.originValue) return false
        if (destinyCurrency != other.destinyCurrency) return false
        if (conversionRate != other.conversionRate) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Conversion(id=$id, userId='$userId', originCurrency='$originCurrency', originValue=$originValue, destinyCurrency='$destinyCurrency', conversionRate=$conversionRate, transactionTime=$transactionTime, destinyValue=$destinyValue)"
    }

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
            conversionRate = destinyRate.divide(originRate, MathContext.DECIMAL32)
        )
    }
}
