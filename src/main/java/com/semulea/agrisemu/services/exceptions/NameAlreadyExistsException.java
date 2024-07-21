package com.semulea.agrisemu.services.exceptions;

public class NameAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NameAlreadyExistsException(String message) {
		super(message);
	}

}
