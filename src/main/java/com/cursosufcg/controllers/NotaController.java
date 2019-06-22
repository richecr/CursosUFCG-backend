package com.cursosufcg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.models.Nota;
import com.cursosufcg.services.NotaService;

@RestController
@RequestMapping({"/v1/nota"})
public class NotaController {

	@Autowired
	private NotaService notaService;
	
	@PostMapping(value = "/avaliar/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Nota> avaliarPerfil(@PathVariable long id, @PathVariable String email, @RequestBody Nota nota) {
		return new ResponseEntity<Nota>( this.notaService.cadastrarNota(id, email, nota), HttpStatus.OK);
	}
}
