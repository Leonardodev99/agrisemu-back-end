package com.semulea.agrisemu.services.exceptions;

public class BiAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BiAlreadyExistsException(String message) {
		super(message);
	}

}
