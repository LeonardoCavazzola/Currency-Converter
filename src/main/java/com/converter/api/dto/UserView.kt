package com.converter.api.dto

import org.springframework.hateoas.RepresentationModel
import com.converter.api.model.User

class UserView(user: User) : RepresentationModel<UserView>() {

    val email: String

    init { // TODO: 14/09/2021 isso é uma boa pratica?
        email = user.email
    }
}