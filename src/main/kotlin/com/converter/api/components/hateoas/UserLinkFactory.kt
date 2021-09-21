package com.converter.api.components.hateoas

import com.converter.api.controller.AuthenticationController
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class UserLinkFactory {

    fun authenticate(): Link  =  WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(AuthenticationController::class.java)
                .authenticate()
        )
            .withRel("authenticate")
            .withType("post")

}