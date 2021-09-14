package com.converter.api.dto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ConversionForm(
    @NotNull @NotBlank @Size(min = 3, max = 3) val originCurrency: String,
    @NotNull val originValue: BigDecimal,
    @NotNull @NotBlank @Size(min = 3, max = 3) val destinyCurrency: String
)