package com.converter.api.domain.service

import com.converter.api.domain.entity.User
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun getAuthenticatedUser(): User
    fun create(email: String, password: String): User
}
