package com.semulea.agrisemu.validation;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class PhoneFormatValidator implements ConstraintValidator<PhoneFormat, String> {
    
    private static final String PHONE_PATTERN = "\\d{3} \\d{3} \\d{3}";

    @Override
    public void initialize(PhoneFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext context) {
        if (phoneField == null) {
            return false;
        }
        return phoneField.matches(PHONE_PATTERN);
    }
}
