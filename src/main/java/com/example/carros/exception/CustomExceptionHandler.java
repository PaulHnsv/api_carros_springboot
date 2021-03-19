package com.example.carros.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	Gson gson = new Gson();

	@ExceptionHandler(EmptyResultDataAccessException.class)
	private ResponseEntity emptyResultDataAccessException(Exception ex) {
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	private ResponseEntity<Object> illegalArgumentException(Exception ex) {
		JsonObject exception = new JsonObject();
		exception.addProperty("message", ex.getMessage());
		
		return ResponseEntity.badRequest().body(exception.toString());
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	private ResponseEntity<Object> accessDeniedException(Exception ex) {
		
		JsonObject exception = new JsonObject();
		exception.addProperty("message", ex.getMessage());
		
		return new ResponseEntity<>(exception.toString(), HttpStatus.FORBIDDEN);
	}
	
	//personalizando um metodo da classe ResponseEntityExceptionHandler para termos um retorno mais apresentável
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<>(gson.toJson(new ExceptionError(ex.getMessage())), status);
	}
}
