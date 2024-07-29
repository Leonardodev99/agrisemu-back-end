package com.semulea.agrisemu.validation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, String> {
	
	private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
	
	@Override
	public void initialize(ValidDateOfBirth constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.isEmpty()) {
			return true;
		}
		
		if(!DATE_PATTERN.matcher(value).matches()) {
			return false;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			formatter.parse(value);
			return true;
		} catch(DateTimeParseException e) {
			return false;
		}
	}

}
