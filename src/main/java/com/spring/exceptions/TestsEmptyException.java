package com.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TestsEmptyException extends Exception {
	
	public TestsEmptyException(String message) {
		super(message);
	}
}
