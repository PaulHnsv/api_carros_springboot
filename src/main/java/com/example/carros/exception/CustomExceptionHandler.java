package com.example.carros.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	Gson gson = new Gson();

	@ExceptionHandler(EmptyResultDataAccessException.class)
	private ResponseEntity emptyResultDataAccessException(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	private ResponseEntity illegalArgumentException(Exception ex) {
		return ResponseEntity.badRequest().body(gson.toJson(ex.getMessage()));
	}
}
