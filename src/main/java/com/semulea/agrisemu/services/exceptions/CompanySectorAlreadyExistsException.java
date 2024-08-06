package com.semulea.agrisemu.services.exceptions;

public class CompanySectorAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CompanySectorAlreadyExistsException(String message) {
		super(message);
	}

}
