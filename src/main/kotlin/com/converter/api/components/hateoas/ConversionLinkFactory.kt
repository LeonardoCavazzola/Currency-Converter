package com.converter.api.components.hateoas

import com.converter.api.controller.ConversionController
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class ConversionLinkFactory {

    fun getAllMyConversions(): Link {
        return WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(ConversionController::class.java)
                .getAll(null, null))
            .withRel("getAllMyConversions")
            .withType("get")
    }

    fun convert(): Link {
        return WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(ConversionController::class.java)
                .convert(null, null)
        )
            .withRel("newConversion")
            .withType("post")
    }
}