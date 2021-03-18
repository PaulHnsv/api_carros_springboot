package com.example.carros.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	Gson gson = new Gson();

	@ExceptionHandler(EmptyResultDataAccessException.class)
	private ResponseEntity emptyResultDataAccessException(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	private ResponseEntity illegalArgumentException(Exception ex) {
		return ResponseEntity.badRequest().body(gson.toJson(ex.getMessage()));
	}
	
	
	//personalizando um metodo da classe ResponseEntityExceptionHandler para termos um retorno mais apresentável
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		return new ResponseEntity<>(new ExceptionError(ex.getMessage()), status);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}
}
