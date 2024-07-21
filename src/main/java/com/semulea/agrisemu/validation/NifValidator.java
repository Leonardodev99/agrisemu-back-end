package com.semulea.agrisemu.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NifValidator implements ConstraintValidator<Nif, String> {

	@Override
	public void initialize(Nif constriantAnnotation) {
		
	}
	
	
	@Override
	public boolean isValid(String nif, ConstraintValidatorContext context) {
		if(nif==null) {
			return true;
		}
		return nif.matches("\\d{10}");
	}

}
