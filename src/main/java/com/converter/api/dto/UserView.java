package com.converter.api.dto;

import com.converter.api.model.User;
import lombok.Getter;

@Getter
public class UserView {

    private final String email;

    public UserView(User user) {
        this.email = user.getEmail();
    }
}
