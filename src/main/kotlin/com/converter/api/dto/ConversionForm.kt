package com.converter.api.dto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ConversionForm(
    // TODO se é not null por que ele está como nullable
    @field:NotNull @field:NotBlank @field:Size(min = 3, max = 3) val originCurrency: String,
    @field:NotNull val originValue: BigDecimal?,
    @field:NotNull @field:NotBlank @field:Size(min = 3, max = 3) val destinyCurrency: String
)