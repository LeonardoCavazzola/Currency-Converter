package com.converter.api.dto;

import com.converter.api.validation.UnregisteredUserEmail;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserForm(
        @NotNull @NotBlank @Email @UnregisteredUserEmail String email,
        @NotNull @NotBlank @Size(min = 8, max = 24) String password) {
}
