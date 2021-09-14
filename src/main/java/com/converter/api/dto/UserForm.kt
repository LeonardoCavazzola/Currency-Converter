package com.converter.api.dto

import com.converter.api.validation.UnregisteredUserEmail
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserForm(
    @NotNull @NotBlank @Email @UnregisteredUserEmail val email: String,
    @NotNull @Size(min = 8, max = 24) val password: String
)