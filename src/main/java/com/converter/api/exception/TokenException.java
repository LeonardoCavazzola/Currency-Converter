package com.converter.api.exception;

public class TokenException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Invalid token";
    }
}
