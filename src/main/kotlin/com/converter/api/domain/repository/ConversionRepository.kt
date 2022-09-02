package com.converter.api.domain.repository

import com.converter.api.domain.entity.Conversion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ConversionRepository {
    fun save(conversion: Conversion): Conversion
    fun findById(id: Long): Conversion?
    fun findAllByUserId(userId: String, pageable: Pageable): Page<Conversion>
}
