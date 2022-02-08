package com.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PatientEmptyException extends Exception {
	
	public PatientEmptyException(String message) {
		super(message);
	}
}
