package com.converter.api.domain.entity

data class User(
    val id: Long? = null,
    val email: String,
    val password: String,
    val authorities: List<Authority> = emptyList()
)
