package com.cursosufcg.exceptions.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsuarioJaExisteException extends RuntimeException {

	public UsuarioJaExisteException(String msg) {
		super(msg);
	}	
}
