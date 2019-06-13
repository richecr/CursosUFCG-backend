package com.cursosufcg.exceptions.autenticacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginIncorretoException extends RuntimeException {

	public LoginIncorretoException(String msg) {
		super(msg);
	}
	
}
