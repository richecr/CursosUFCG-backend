package com.cursosufcg.exceptions.email;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNaoEnviadoException extends RuntimeException {

	public EmailNaoEnviadoException(String msg) {
		super(msg);
	}
	
}
