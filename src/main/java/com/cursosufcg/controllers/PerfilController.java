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

@RestController
@RequestMapping({"/v1/perfil"})
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@PostMapping(value = "/{idDisciplina}")
	@ResponseBody
	public ResponseEntity<Perfil> cadastrarPerfil(@PathVariable long idDisciplina, @RequestBody Perfil perfil) {
		return new ResponseEntity<Perfil>( this.perfilService.cadastrarPerfil(idDisciplina, perfil), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Perfil> findPerfil(@PathVariable long id, @RequestParam(name="email", required=true) String email) {
		return new ResponseEntity<Perfil>(this.perfilService.procurarPorId(id, email), HttpStatus.OK);
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Perfil>> buscarTodosPerfils() {
		return new ResponseEntity<List<Perfil>>(this.perfilService.buscarTodos(), HttpStatus.OK);
	}

	@PostMapping(value = "/curtir/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Perfil> curtir(@PathVariable long id, @PathVariable String email) {
		return new ResponseEntity<Perfil>( this.perfilService.curtirPerfil(id, email), HttpStatus.CREATED);
	}
}