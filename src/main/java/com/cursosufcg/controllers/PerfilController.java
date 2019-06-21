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
	public ResponseEntity<Perfil> create(@PathVariable long idDisciplina, @RequestBody Perfil perfil) {
		return new ResponseEntity<Perfil>( this.perfilService.create(idDisciplina, perfil), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Perfil> findPerfil(@PathVariable long id, @RequestParam(name="email", required=true) String email) {
		return new ResponseEntity<Perfil>(this.perfilService.findById(id, email), HttpStatus.OK);
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Perfil>> findAllPerfil() {
		return new ResponseEntity<List<Perfil>>(this.perfilService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/curtir/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Perfil> curtir(@PathVariable long id, @PathVariable String email) {
		return new ResponseEntity<Perfil>( this.perfilService.createCurtida(id, email), HttpStatus.CREATED);
	}

	@PostMapping(value = "/comentar/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> comentar(@PathVariable long id, @PathVariable String email, @RequestBody Comentario comentario) {
		return new ResponseEntity<Comentario>( this.perfilService.createComentario(id, email, comentario), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/responderComentario/{idComentario}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> responderComentario(@PathVariable long idComentario, @PathVariable String email, @RequestBody Comentario comentario) {
		return new ResponseEntity<Comentario>( this.perfilService.createResponderComentario(idComentario, email, comentario), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/comentario/{idComentario}")
	@ResponseBody
	public ResponseEntity<Comentario> getComentario(@PathVariable long idComentario) {
		return new ResponseEntity<Comentario>( this.perfilService.getComentario(idComentario), HttpStatus.CREATED);
	}

	@PostMapping(value = "/avaliar/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Nota> avaliarPerfil(@PathVariable long id,@PathVariable String email, @RequestBody Nota nota) {
		return new ResponseEntity<Nota>( this.perfilService.createNota(id, email, nota), HttpStatus.OK);
	}

	@DeleteMapping(value = "/apagarComentario/{idPerfil}/{idComentario}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> apagaComentario(@PathVariable long idPerfil, 
												  @PathVariable long idComentario, 
												  @PathVariable String email) {
		return new ResponseEntity<Comentario>( this.perfilService.apagaComentario(idPerfil, idComentario, email), HttpStatus.OK);
	}
}