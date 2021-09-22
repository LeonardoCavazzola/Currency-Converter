package com.converter.api.dto

import org.springframework.hateoas.RepresentationModel
import com.converter.api.model.User

class UserView(user: User) : RepresentationModel<UserView>() {

    val email: String

    init { // TODO: Segue a mesma linha da classe ConversionView.kt
        email = user.email
    }
}