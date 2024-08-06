package com.semulea.agrisemu.validation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FinalDateValidator implements ConstraintValidator<ValidFinalDate, String> {
	
	private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm";
	
	@Override
	public void initialize(ValidFinalDate constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.isEmpty()) {
			return true;
		}
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
			formatter.parse(value);
			return true;
		} catch(DateTimeParseException e) {
			return false;
		}
	}

}
