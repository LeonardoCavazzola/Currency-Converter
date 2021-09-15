package com.converter.api.controller

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import com.converter.api.dto.ExpetionView
import org.springframework.validation.FieldError
import com.converter.api.exception.CurrenciesDontExistOrArentAvailableException
import com.converter.api.exception.TokenException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.ArrayList
import java.util.function.Consumer

@RestControllerAdvice
class ExceptionHandlerController {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): List<ExpetionView> {

        val dto: MutableList<ExpetionView> = ArrayList()
        val fieldErrors = exception.bindingResult.fieldErrors

        fieldErrors.forEach(Consumer { e: FieldError ->
            val error = ExpetionView(
                field = e.field,
                error = e.defaultMessage!!
            )
            dto.add(error)
        })
        return dto
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CurrenciesDontExistOrArentAvailableException::class)
    fun handle(exception: CurrenciesDontExistOrArentAvailableException): ExpetionView {
        return ExpetionView(
            field = "originCurrency and/or destinyCurrency",
            error = exception.message
        )
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenException::class)
    fun handle(exception: TokenException): ExpetionView {
        return ExpetionView(
            field = "Bearer Token",
            error = exception.message
        )
    }
}