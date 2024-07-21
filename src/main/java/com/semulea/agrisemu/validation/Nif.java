package com.semulea.agrisemu.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NifValidator.class)
public @interface Nif {
	
	String message() default "NIF must have 10 digits number";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
