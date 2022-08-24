package com.converter.api.controller

import com.converter.api.controller.dto.ExceptionOutput
import com.converter.api.exception.CurrencyDoesntExistOrArentAvailableException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlerController {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): List<ExceptionOutput> {
        val fieldErrors = exception.bindingResult.fieldErrors
        return fieldErrors.map {
            ExceptionOutput(
                field = it.field,
                error = it.defaultMessage!!
            )
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CurrencyDoesntExistOrArentAvailableException::class)
    fun handle(exception: CurrencyDoesntExistOrArentAvailableException): ExceptionOutput = ExceptionOutput(
        field = "originCurrency and/or destinyCurrency",
        error = exception.message
    )
}
