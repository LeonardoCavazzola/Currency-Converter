package com.converter.api.domain.service

import com.converter.api.domain.entity.Conversion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.math.BigDecimal

interface ConversionService {
    fun getMyConversions(pageable: Pageable): Page<Conversion>
    fun convert(originCurrency: String, destinyCurrency: String, originValue: BigDecimal): Conversion
    fun create(conversion: Conversion): Conversion
}
