package com.cursosufcg.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.models.LoginResponse;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.services.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RestController
@RequestMapping({"/v1/login"})
public class LoginController {
	
	private final String TOKEN_KEY = "bananas";

	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping(value = "/")
	@ResponseBody
	private LoginResponse authenticate(@RequestBody Usuario usuario) {
		Usuario u = this.usuarioService.findByEmail(usuario.getEmail());
		
		if (u == null) {
			throw new RuntimeException("Usuário não cadastrado!");
		}
		
		if (!(u.getSenha().equals(usuario.getSenha()))) {
			throw new RuntimeException("Autenticação incorreta!");
		}
		
		String token = Jwts.builder().
				setSubject(u.getEmail()).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
				setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();

		return new LoginResponse(token);
	}
}
