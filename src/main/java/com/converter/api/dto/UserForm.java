package com.converter.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserForm(
        @NotNull @NotBlank @Email String email,
        @NotNull @NotBlank @Size(min = 8) String password) {
}
