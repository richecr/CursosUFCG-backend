package com.cursosufcg.controllers;

import java.util.List;

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

import com.cursosufcg.models.Disciplina;
import com.cursosufcg.services.DisciplinaService;

@RestController
@RequestMapping({"/v1/disciplina"})
public class DisciplinaController {

	private DisciplinaService disciplinaService;

	public DisciplinaController(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina disciplina) {
		return new ResponseEntity<Disciplina>( this.disciplinaService.cadastrarDisciplina(disciplina), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/admin")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> cadastrarVariasDisciplinas(@RequestBody List<Disciplina> disciplinas) {
		return new ResponseEntity<List<Disciplina>>( this.disciplinaService.cadastrarVariasDisciplinas(disciplinas), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> buscarPeloId(@PathVariable long id) {
		return new ResponseEntity<Disciplina>( this.disciplinaService.buscarPeloId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Disciplina>> buscarTodas() {
		return new ResponseEntity<List<Disciplina>>( this.disciplinaService.buscarTodas(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscar/{query}")
	public ResponseEntity<List<Disciplina>> buscarPorQuery(@PathVariable String query) {
		return new ResponseEntity<List<Disciplina>>( this.disciplinaService.buscarPorQuery(query.toUpperCase()), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletarPeloId(@PathVariable long id) {
		this.disciplinaService.deletarPeloId(id);
	}
}
