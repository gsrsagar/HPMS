package com.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InValidNumericalException extends Exception{
	
	public InValidNumericalException(String message) {
		super(message);
	}
}
