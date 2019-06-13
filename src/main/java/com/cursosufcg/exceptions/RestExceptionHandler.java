package com.cursosufcg.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cursosufcg.exceptions.autenticacao.LoginIncorretoException;
import com.cursosufcg.exceptions.autenticacao.TokenIncorretoException;
import com.cursosufcg.exceptions.email.EmailNaoEnviadoException;
import com.cursosufcg.exceptions.usuario.UsuarioJaExisteException;
import com.cursosufcg.exceptions.usuario.UsuarioNaoEncontradoException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(Exception.class)
    public ResponseEntity<CustomRestError> handleAnyException(Exception ex, WebRequest request) {
	    CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));

	    return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	 @ExceptionHandler({UsuarioNaoEncontradoException.class})
	 public ResponseEntity<CustomRestError> notFound(Exception ex, WebRequest request) {
		 CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));

		 return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler({UsuarioJaExisteException.class})
	 public ResponseEntity<CustomRestError> usuarioJaExiste(Exception ex, WebRequest request) {
		 CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));

		 return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler({LoginIncorretoException.class})
	 public ResponseEntity<CustomRestError> loginIncorreto(Exception ex, WebRequest request) {
		 CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));

		 return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler({TokenIncorretoException.class})
	 public ResponseEntity<CustomRestError> tokenIncorreto(Exception ex, WebRequest request) {
		 CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));

		 return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler({EmailNaoEnviadoException.class})
	 public ResponseEntity<CustomRestError> emailNaoEnviado(Exception ex, WebRequest request) {
		 CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));

		 return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	 }
	
}
