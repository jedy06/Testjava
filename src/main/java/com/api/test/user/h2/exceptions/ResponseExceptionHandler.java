package com.api.test.user.h2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;



@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
		
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		//ex.getBindingResult().getAllErrors();
		ExceptionResponse exceptionResponse = new ExceptionResponse("Validacion fallida");
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> manejarModeloExcepciones(ModelNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> manejarModeloExcepciones(UsernameNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Usuario no encontrado");
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
}

