package com.converter.api.controller.dto

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

data class AuthForm(
    val email: String,
    val password: String
) {
    fun toUsernamePasswordAuthenticationToken() = UsernamePasswordAuthenticationToken(email, password)
}
