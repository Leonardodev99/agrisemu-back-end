package com.semulea.agrisemu.services.exceptions;

public class DateOfBirthAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DateOfBirthAlreadyExistsException(String message) {
		super(message);
	}

}
