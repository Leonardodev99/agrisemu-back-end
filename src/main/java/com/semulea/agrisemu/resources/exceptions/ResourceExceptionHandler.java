package com.semulea.agrisemu.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	 @ExceptionHandler(NumberFormatException.class)
	    public ResponseEntity<StandardError> numberFormatException(NumberFormatException e, HttpServletRequest request) {
	      	String error =  "Invalid ID format";
	      	HttpStatus status = HttpStatus.NOT_FOUND;
	      	StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
	      	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	    }
	 
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<ValidationError> validationException(MethodArgumentNotValidException e, HttpServletRequest request) {
		 String error = "Validation error";
		 HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		 ValidationError err = new ValidationError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		 for(FieldError f: e.getBindingResult().getFieldErrors()) {
			 err.addError(f.getField(), f.getDefaultMessage());
		 }
		 return ResponseEntity.status(status).body(err);
	 }
	 
	 @ExceptionHandler(EmailAlreadyExistsException.class)
	 public ResponseEntity<StandardError> emailAlreadyExistsException(EmailAlreadyExistsException e,HttpServletRequest request) {
		 String error =  "User not be created";
	      	HttpStatus status = HttpStatus.BAD_REQUEST;
	      	StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
	      	return ResponseEntity.status(status).body(err);
	 }

}
