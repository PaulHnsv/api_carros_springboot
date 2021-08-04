package com.example.carros.exception;

import java.io.Serializable;

class ExceptionError implements Serializable{

	private static final long serialVersionUID = 1L;
	private String error;
	
	public ExceptionError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
}