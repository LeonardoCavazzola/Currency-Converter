package com.converter.api.domain.entity

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class User(
    val id: Long? = null,
    val email: String,
    val password: String,
) {
    companion object {
        fun of(email: String, password: String) =
            User(
                email = email,
                password =  BCryptPasswordEncoder().encode(password),
            )
    }
}
