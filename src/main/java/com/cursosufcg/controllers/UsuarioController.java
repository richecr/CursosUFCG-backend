package com.cursosufcg.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.models.Usuario;
import com.cursosufcg.services.UsuarioService;

@RestController
@RequestMapping({"/v1/usuario"})
public class UsuarioController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Usuario> listaUsuario(@PathVariable String email) {
		return new ResponseEntity<Usuario>( this.usuarioService.findByEmail(email), HttpStatus.OK );
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>( this.usuarioService.create(usuario), HttpStatus.CREATED );
	}
}
