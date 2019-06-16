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

import com.cursosufcg.models.Curtida;
import com.cursosufcg.services.CurtidaService;

@RestController
@RequestMapping({"/v1/curtida"})
public class CurtidaController {

	@Autowired
	private CurtidaService curtidaService;

	@PostMapping(value = "/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Curtida> create(@PathVariable long id, @PathVariable String email, @RequestBody Curtida curtida) {
		System.out.println("OIII");
		return new ResponseEntity<Curtida>( this.curtidaService.create(id, email, curtida), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/usuario/{email}")
	public ResponseEntity<List<Curtida>> findByUsuario(@PathVariable String email) {
		return new ResponseEntity<List<Curtida>>( this.curtidaService.findByUsuario(email), HttpStatus.OK);
	}
	
	@GetMapping(value = "/perfil/{id}")
	public ResponseEntity<List<Curtida>> findByUsuario(@PathVariable long id) {
		return new ResponseEntity<List<Curtida>>( this.curtidaService.findByPerfil(id), HttpStatus.OK);
	}
}
