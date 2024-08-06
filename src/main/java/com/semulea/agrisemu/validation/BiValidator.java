package com.semulea.agrisemu.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BiValidator implements ConstraintValidator<BiValidation, String> {

    private static final String BI_PATTERN = "0\\d{8}[A-Z]{2}\\d{3}";

    @Override
    public void initialize(BiValidation constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String bi, ConstraintValidatorContext context) {
        if (bi == null || bi.isEmpty()) {
            return false;
        }
        return bi.matches(BI_PATTERN);
    }
}
