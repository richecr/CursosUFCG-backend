package com.cursosufcg.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.exceptions.usuario.UsuarioJaExisteException;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.services.UsuarioService;
import com.cursosufcg.utils.*;

@RestController
@RequestMapping({"/v1/usuario"})
public class UsuarioController {

	private UsuarioService usuarioService;
	private EnviarEmail enviarEmail;
	
	public UsuarioController(UsuarioService usuarioService, EnviarEmail enviarEmail) {
		this.usuarioService = usuarioService;
		this.enviarEmail = enviarEmail;
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Usuario> listaUsuario(@PathVariable String email) {
		return new ResponseEntity<Usuario>( this.usuarioService.findByEmail(email), HttpStatus.OK );
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		Usuario u = this.usuarioService.create(usuario);

		if (u == null) {
			throw new UsuarioJaExisteException("Usuário já cadastrado");
		}
		this.enviarEmail.EnviarEmail(u.getEmail());
		
		return new ResponseEntity<Usuario>( u, HttpStatus.CREATED );
	}
	
	@DeleteMapping(value = "/{email}")
	public ResponseEntity delete(@PathVariable String email) {
		this.usuarioService.deleteById(email);

		return new ResponseEntity(HttpStatus.OK);
	}
}
