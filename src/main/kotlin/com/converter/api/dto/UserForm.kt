package com.converter.api.dto

import com.converter.api.validation.UnregisteredUserEmail
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserForm(
    @field:NotNull @field:NotBlank @field:Email @field:UnregisteredUserEmail val email: String?,
    @field:NotNull @field:Size(min = 8, max = 24) val password: String?
)