package com.converter.api.domain.service

import com.converter.api.domain.entity.User
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun findById(id: Long): User?
    fun create(user: User): User
}
