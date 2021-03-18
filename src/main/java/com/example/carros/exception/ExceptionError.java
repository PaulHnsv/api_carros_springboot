package com.example.carros.exception;

import java.io.Serializable;

class ExceptionError implements Serializable{
	private String error;
	
	public ExceptionError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
}