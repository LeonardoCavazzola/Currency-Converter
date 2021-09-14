package com.converter.api.validation

import com.converter.api.repository.UserRepository
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UnregisteredUserEmailValidator(
    private val userRepository: UserRepository
) : ConstraintValidator<UnregisteredUserEmail, String?> {

    override fun isValid(email: String?, constraintValidatorContext: ConstraintValidatorContext): Boolean {
        return if (email == null) {
            true
        } else {
            !userRepository.existsByEmail(email)
        }
    }
}