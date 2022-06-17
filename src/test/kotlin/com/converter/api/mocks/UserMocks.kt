package com.converter.api.mocks

import com.converter.api.domain.entity.User
import com.converter.api.infra.model.UserModel

object UserMocks {
    fun entity() = User(
        id = 1,
        email = "email",
        password = "password"
    )

    fun model() = UserModel(
        id = 1,
        email = "email",
        password = "password"
    )
}
