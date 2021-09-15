package com.converter.api.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [UnregisteredUserEmailValidator::class])
@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class UnregisteredUserEmail(
    val message: String = "There is already an user with this email",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)