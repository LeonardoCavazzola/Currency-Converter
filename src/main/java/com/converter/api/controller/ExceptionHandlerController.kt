package com.converter.api.controller;

import com.converter.api.dto.ExpetionView;
import com.converter.api.exception.CurrenciesDontExistOrArentAvailableException;
import com.converter.api.exception.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExpetionView> handle(MethodArgumentNotValidException exception) {
        List<ExpetionView> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            ExpetionView error = new ExpetionView(e.getField(), e.getDefaultMessage());
            dto.add(error);
        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CurrenciesDontExistOrArentAvailableException.class)
    public ExpetionView handle(CurrenciesDontExistOrArentAvailableException exception) {

        return new ExpetionView("originCurrency and/or destinyCurrency", exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenException.class)
    public ExpetionView handle(TokenException exception) {

        return new ExpetionView("Bearer Token", exception.getMessage());
    }

}
