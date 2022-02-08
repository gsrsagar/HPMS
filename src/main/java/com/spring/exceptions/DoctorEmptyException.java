package com.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DoctorEmptyException  extends Exception {
	
	public DoctorEmptyException(String message) {
		super(message);
	}
}
