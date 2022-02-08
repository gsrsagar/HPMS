package com.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserEmptyException extends RuntimeException{
	
	public UserEmptyException(String message) {
		super(message);
	}
}
