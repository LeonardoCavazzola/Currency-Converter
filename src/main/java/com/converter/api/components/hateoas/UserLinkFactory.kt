package com.converter.api.components.hateoas;

import com.converter.api.controller.AuthenticationController;
import com.converter.api.controller.ConversionController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserLinkFactory {

    public Link getAuthLink(){
        return WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(AuthenticationController.class)
                        .autenticar(null))
                .withRel("authenticate")
                .withType("post");
    }
}
