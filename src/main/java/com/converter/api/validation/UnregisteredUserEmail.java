package com.converter.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UnregisteredUserEmailValidator.class)
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnregisteredUserEmail {

    String message() default "There is already an user with this email";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
