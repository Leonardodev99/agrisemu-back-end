package com.semulea.agrisemu.services.exceptions;

public class NifAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NifAlreadyExistsException(String message) {
		super(message);
	}

}
