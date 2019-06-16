package com.cursosufcg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.models.Comentario;
import com.cursosufcg.services.ComentarioService;

@RestController
@RequestMapping({"/v1/comentario"})
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;
	
	@PostMapping(value = "/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> create(@PathVariable long id, @PathVariable String email, @RequestBody Comentario comentario) {
		return new ResponseEntity<Comentario>( this.comentarioService.create(id, email, comentario), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/usuario/{email}")
	public ResponseEntity<List<Comentario>> findByUsuario(@PathVariable String email) {
		return new ResponseEntity<List<Comentario>>( this.comentarioService.findByUsuario(email), HttpStatus.OK);
	}
	
	@GetMapping(value = "/perfil/{id}")
	public ResponseEntity<List<Comentario>> findByPerfil(@PathVariable long id) {
		return new ResponseEntity<List<Comentario>>( this.comentarioService.findByPerfil(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<Comentario> createresposta(@PathVariable long id, @RequestBody Comentario comentario) {
		return new ResponseEntity<Comentario>( this.comentarioService.createResposta(id, comentario), HttpStatus.CREATED);
	}
}