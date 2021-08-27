package com.converter.api.validation;

import com.converter.api.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnregisteredUserEmailValidator implements ConstraintValidator<UnregisteredUserEmail, String> {

    private final UserRepository userRepository;

    public UnregisteredUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null) {
            return true;
        } else {
            return !userRepository.existsByEmail(email);
        }
    }
}
