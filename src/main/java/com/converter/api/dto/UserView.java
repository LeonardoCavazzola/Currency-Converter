package com.converter.api.dto;

import com.converter.api.model.User;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class UserView extends RepresentationModel<UserView> {

    private final String email;

    public UserView(User user) {
        this.email = user.getEmail();
    }
}
