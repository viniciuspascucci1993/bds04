package com.devsuperior.bds04.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	// MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation( MethodArgumentNotValidException e , HttpServletRequest request ) {
		
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		ValidationError validationError = new ValidationError();
		validationError.setTimeStamp(Instant.now());
		validationError.setStatus(status.value());
		validationError.setError("Valtidation Exception");
		validationError.setMessage(e.getMessage());
		validationError.setPath(request.getRequestURI());
		
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(validationError);
	}
}
