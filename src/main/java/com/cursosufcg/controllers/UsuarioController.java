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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({"/v1/usuario"})
@Api(value= "Controller de Usuário")
public class UsuarioController {

	private UsuarioService usuarioService;
	private EnviarEmail enviarEmail;
	
	public UsuarioController(UsuarioService usuarioService, EnviarEmail enviarEmail) {
		this.usuarioService = usuarioService;
		this.enviarEmail = enviarEmail;
	}
	
	@ApiOperation(value = "Retorna usuário pelo email")
	@GetMapping("/{email}")
	public ResponseEntity<Usuario> listarUsuario(@PathVariable String email) {
		return new ResponseEntity<Usuario>( this.usuarioService.buscarPorEmail(email), HttpStatus.OK );
	}
	
	@ApiOperation(value = "Cria um usuário")
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		Usuario u = this.usuarioService.cadastrarUsuario(usuario);

		if (u == null) {
			throw new UsuarioJaExisteException("Usuário já cadastrado");
		}
		this.enviarEmail.enviarEmail(u.getEmail());
		
		return new ResponseEntity<Usuario>( u, HttpStatus.CREATED );
	}
	
	@ApiOperation(value = "Apaga um usuário")
	@DeleteMapping(value = "/{email}")
	public ResponseEntity deletarUsuario(@PathVariable String email) {
		this.usuarioService.deletarPeloId(email);

		return new ResponseEntity(HttpStatus.OK);
	}
}
