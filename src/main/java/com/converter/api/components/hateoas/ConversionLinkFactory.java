package com.converter.api.components.hateoas;

import com.converter.api.controller.ConversionController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConversionLinkFactory {

    public Link getAllMyConversions(){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(ConversionController.class)
                        .getAll(null, null))
                .withRel("getAllMyConversions")
                .withType("get");
    }

    public Link convert(){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(ConversionController.class)
                        .convert(null,null))
                .withRel("newConversion")
                .withType("post");
    }
}
