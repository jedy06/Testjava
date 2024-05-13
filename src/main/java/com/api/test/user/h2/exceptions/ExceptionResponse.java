package com.api.test.user.h2.exceptions;

public class ExceptionResponse {

	private String mensaje;
	
	public ExceptionResponse(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
