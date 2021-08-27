package com.converter.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record ConversionForm(
         @NotNull @NotBlank @Size(min = 3, max = 3) String originCurrency,
         @NotNull BigDecimal originValue,
         @NotNull @NotBlank @Size(min = 3, max = 3) String destinyCurrency) {
}
