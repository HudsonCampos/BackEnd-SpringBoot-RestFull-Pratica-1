package com.curso.poo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyFileNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MyFileNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public MyFileNotFound(String message) {
		super(message);
	}

	
}
