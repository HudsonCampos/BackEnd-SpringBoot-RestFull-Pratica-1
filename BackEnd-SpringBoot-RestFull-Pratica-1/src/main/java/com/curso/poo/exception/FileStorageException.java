package com.curso.poo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String Exception) {
		super(Exception);
	}
	
	public FileStorageException(String Exception, Throwable cause) {
		super(Exception, cause);
	}

}
