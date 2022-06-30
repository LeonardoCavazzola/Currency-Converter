package com.converter.api.domain.repository

import com.converter.api.domain.entity.Conversion
import com.converter.api.domain.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ConversionRepository {
    fun save(conversion: Conversion): Conversion
    fun findAllByUser(user: User, pageable: Pageable): Page<Conversion>
}
