package com.cursosufcg.exceptions.autenticacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class TokenIncorretoException extends RuntimeException {

	public TokenIncorretoException(String msg) {
		super(msg);
	}
}
