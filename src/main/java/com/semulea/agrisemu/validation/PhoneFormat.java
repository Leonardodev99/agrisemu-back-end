package com.semulea.agrisemu.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneFormatValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneFormat {
    String message() default "Phone number must be in the format xxx xxx xxx";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

