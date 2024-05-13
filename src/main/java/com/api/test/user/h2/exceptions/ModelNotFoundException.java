package com.api.test.user.h2.exceptions;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException{

	public ModelNotFoundException(String mensaje) {
		super(mensaje);
	}
}