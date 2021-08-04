package com.example.carros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//não é possível fazer personalizações de retorno além do campo message depois de usar essa anotation
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

	//construtor que por meio do super() chama o construtor da classe pai e executa o código de mesmo nome dela
	public ObjectNotFoundException(String message) {
		super(message);
		}
	
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
		
}
