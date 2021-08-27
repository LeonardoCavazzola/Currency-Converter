package com.converter.api.components.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public interface LinkFactory {

    Link getAllMyConversions();
    Link convert();
}
