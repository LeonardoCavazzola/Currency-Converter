package com.converter.api.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.math.MathContext
import java.time.ZoneId
import javax.persistence.*

@Entity
data class Conversion(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne val user: User,
    @Column(nullable = false) val originCur: String,
    @Column(nullable = false, scale = 6, precision = 18) val originValue: BigDecimal,
    @Column(nullable = false) val destinyCur: String,
    @Column(nullable = false, scale = 6, precision = 18) val conversionRate: BigDecimal,
    @Column(nullable = false) val transactionTime: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC"))
) {
    fun getDestinyValue(): BigDecimal { // TODO: 14/09/2021 rever isso, porque cheira mal demais
        return originValue.multiply(conversionRate, MathContext.DECIMAL32)
    }
}