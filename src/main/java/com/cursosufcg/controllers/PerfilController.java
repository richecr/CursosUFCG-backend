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
import com.cursosufcg.models.Curtida;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.services.PerfilService;

@RestController
@RequestMapping({"/v1/perfil"})
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@PostMapping(value = "/{idDisciplina}")
	@ResponseBody
	public ResponseEntity<Perfil> create(@PathVariable long idDisciplina, @RequestBody Perfil perfil) {
		return new ResponseEntity<Perfil>( this.perfilService.create(idDisciplina, perfil), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Perfil> findPerfil(@PathVariable long id) {
		
		return new ResponseEntity<Perfil>(this.perfilService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Perfil>> findAllPerfil() {
		return new ResponseEntity<List<Perfil>>(this.perfilService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/curtir/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Curtida> curtir(@PathVariable long id, @PathVariable String email, @RequestBody Curtida curtida) {
		return new ResponseEntity<Curtida>( this.perfilService.createCurtida(id, email, curtida), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/comentar/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> comentar(@PathVariable long id, @PathVariable String email, @RequestBody Comentario comentario) {
		return new ResponseEntity<Comentario>( this.perfilService.createComentario(id, email, comentario), HttpStatus.CREATED);
	}
}
