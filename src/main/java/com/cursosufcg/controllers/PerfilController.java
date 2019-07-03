package com.cursosufcg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.models.Comentario;
import com.cursosufcg.models.Nota;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.services.PerfilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({"/v1/perfil"})
@Api(value = "Controller de Perfil", description = "Responsável por controlar as funcionalidades que um perfil possui")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@ApiOperation(value = "Cadastra um perfil")
	@PostMapping(value = "/{idDisciplina}")
	@ResponseBody
	public ResponseEntity<Perfil> cadastrarPerfil(@PathVariable long idDisciplina, @RequestBody Perfil perfil) {
		return new ResponseEntity<Perfil>( this.perfilService.cadastrarPerfil(idDisciplina, perfil), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retorna um perfil pelo id")
	@GetMapping(value = "/{id}/{email}")
	public ResponseEntity<Perfil> findPerfil(@PathVariable long id, @PathVariable String email) {
		return new ResponseEntity<Perfil>(this.perfilService.procurarPorId(id, email), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna perfis ordenados por quantidade de like")
	@GetMapping(value = "/ordenarPorLikes")
	public ResponseEntity<List<Perfil>> findPerfilOrderedByLikes() {
		return new ResponseEntity<List<Perfil>>( this.perfilService.findPerfilOrderedByLikes(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna perfis ordenados por quantidade de comentários")
	@GetMapping(value = "/ordenarPorComentarios")
	public ResponseEntity<List<Perfil>> findPerfilOrderedByComments() {
		return new ResponseEntity<List<Perfil>>( this.perfilService.findPerfilOrderedByComments(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna todos os perfis")
	@GetMapping(value = "/")
	public ResponseEntity<List<Perfil>> buscarTodosPerfils() {
		return new ResponseEntity<List<Perfil>>(this.perfilService.buscarTodos(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna todos os perfis por substring")
	@GetMapping(value = "/buscar/{query}")
	@ResponseBody
	public ResponseEntity<List<Perfil>> buscarTodosPerfilsPelaQuery(@PathVariable String query) {
		return new ResponseEntity<List<Perfil>>(this.perfilService.buscarTodosPerfilsPelaQuery(query.toUpperCase()), HttpStatus.OK);
	}

	@ApiOperation(value = "Cria um like no perfil")
	@PostMapping(value = "/curtir/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Perfil> curtir(@PathVariable long id, @PathVariable String email) {
		return new ResponseEntity<Perfil>( this.perfilService.curtirPerfil(id, email), HttpStatus.CREATED);
	}
}