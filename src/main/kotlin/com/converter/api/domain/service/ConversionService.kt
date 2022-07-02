package com.converter.api.domain.service

import com.converter.api.domain.entity.Conversion
import com.converter.api.domain.entity.ConversionIntent
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ConversionService {
    fun getMyConversions(pageable: Pageable): Page<Conversion>
    fun convert(intent: ConversionIntent): Conversion
}
