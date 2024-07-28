package com.semulea.agrisemu.services.exceptions;

public class NameEmployerAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NameEmployerAlreadyExistsException(String message) {
		super(message);
	}

}
