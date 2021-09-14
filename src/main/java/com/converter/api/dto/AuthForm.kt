package com.converter.api.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record AuthForm(
        String email,
        String password) {

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
