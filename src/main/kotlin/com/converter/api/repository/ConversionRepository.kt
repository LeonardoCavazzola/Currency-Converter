package com.converter.api.repository

import com.converter.api.model.Conversion
import com.converter.api.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ConversionRepository : JpaRepository<Conversion, Long?> {
    fun findAllByUser(user: User, pageable: Pageable?): Page<Conversion>
}