package com.semulea.agrisemu.services.exceptions;

public class WorkerAlreadyInDepartmentException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public WorkerAlreadyInDepartmentException(String message) {
		super(message);
	}

}
