package com.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LabEmptyException extends Exception{
	
	public LabEmptyException(String message) {
		super(message);
	}
}
