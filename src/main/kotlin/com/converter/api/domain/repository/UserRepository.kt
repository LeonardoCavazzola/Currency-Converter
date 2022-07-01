package com.converter.api.domain.repository

import com.converter.api.domain.entity.User

interface UserRepository {
    fun findByEmail(email: String): User?
    fun save(user: User): User
    fun existsByEmail(email: String): Boolean
}
