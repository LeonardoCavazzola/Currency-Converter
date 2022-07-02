package com.converter.api.controller.dto

import com.converter.api.domain.entity.User

class UserOutput(
    val email: String,
)

fun User.toOutput() = UserOutput(
    email = email,
)
