package com.converter.api.components.hateoas

import com.converter.api.controller.ConversionController
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class ConversionLinkFactory {

    fun getAllMyConversions() = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(ConversionController::class.java)
                .getAll())
            .withRel("getAllMyConversions")
            .withType("get")


    fun convert(): Link  =
         WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(ConversionController::class.java)
                .convert())
            .withRel("newConversion")
            .withType("post")

}