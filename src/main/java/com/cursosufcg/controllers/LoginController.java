package com.cursosufcg.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.exceptions.autenticacao.LoginIncorretoException;
import com.cursosufcg.models.LoginResponse;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.services.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping({"/v1/login"})
@Api(value = "Controller de Login", description = "Responsável por controlar as funcionalidades de login")
public class LoginController {
	
	private final String TOKEN_KEY = "bananas";

	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation(value = "Realiza login de um usuário")
	@PostMapping(value = "/")
	@ResponseBody
	private LoginResponse authenticate(@RequestBody Usuario usuario) {
		Usuario u = this.usuarioService.buscarPorEmail(usuario.getEmail());
		
		if (u == null) {
			throw new LoginIncorretoException("Usuário não cadastrado!");
		}
		
		if (!(u.getSenha().equals(usuario.getSenha()))) {
			throw new LoginIncorretoException("Autenticação incorreta!");
		}
		
		String token = Jwts.builder().
				setSubject(u.getEmail()).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.compact();

		return new LoginResponse(token);
	}
}
