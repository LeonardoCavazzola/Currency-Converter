package com.converter.api.exception;

public class CurrenciesDontExistOrArentAvailableException extends RuntimeException {

    @Override
    public String getMessage() {
        return "One or more Currencies do not exist or are not available";
    }
}
