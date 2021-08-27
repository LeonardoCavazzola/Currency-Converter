package com.converter.api.exception;

public class CurrenciesDontExistOrArentAvailableException extends RuntimeException {

    @Override
    public String getMessage() {
        return "One or more currencies do not exist or are not available";
    }
}
