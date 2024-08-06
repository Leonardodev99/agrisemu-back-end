package com.semulea.agrisemu.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = BiValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BiValidation {
    String message() default "Invalid BI format. It should have the format 0XXXXXXXXAA000.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
