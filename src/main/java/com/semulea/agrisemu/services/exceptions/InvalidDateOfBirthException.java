package com.semulea.agrisemu.services.exceptions;

public class InvalidDateOfBirthException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDateOfBirthException(String message) {
		super(message);
	}

}
