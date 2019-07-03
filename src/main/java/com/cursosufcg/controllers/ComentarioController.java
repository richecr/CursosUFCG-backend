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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursosufcg.models.Comentario;
import com.cursosufcg.services.ComentarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({"/v1/comentario"})
@Api(value = "Controller de Comentário", description = "Responsável por controlar as funcionalidades que um comentário possui")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@ApiOperation(value = "Cria um comentário")
	@PostMapping(value = "/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> comentar(@PathVariable long id, @PathVariable String email, @RequestBody Comentario comentario) {
		return new ResponseEntity<Comentario>( this.comentarioService.comentar(id, email, comentario), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retorna todos os comentários de um usuário")
	@GetMapping(value = "/usuario/{email}")
	public ResponseEntity<List<Comentario>> buscarPorUsuario(@PathVariable String email) {
		return new ResponseEntity<List<Comentario>>( this.comentarioService.buscarPorUsuario(email), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna as respostas de um comentário")
	@GetMapping(value = "/respostas/{id}/{email}")
	@ResponseBody
	public ResponseEntity<List<Comentario>> buscarRespostas(@PathVariable long id, @PathVariable String email) {
		return new ResponseEntity<List<Comentario>>( this.comentarioService.buscarRespostas(id, email), HttpStatus.OK);
	}

	@ApiOperation(value = "Cria resposta de um comentário")
	@PostMapping(value = "/responderComentario/{idComentario}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> responderComentario(@PathVariable long idComentario, @PathVariable String email, @RequestBody Comentario comentario) {
		return new ResponseEntity<Comentario>( this.comentarioService.responderComentario(idComentario, email, comentario), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Apaga um comentário de um perfil")
	@DeleteMapping(value = "/apagarComentario/{idPerfil}/{idComentario}/{email}")
	@ResponseBody
	public ResponseEntity<Comentario> apagarComentario(@PathVariable long idPerfil, 
												  @PathVariable long idComentario, 
												  @PathVariable String email) {
		return new ResponseEntity<Comentario>( this.comentarioService.apagarComentario(idPerfil, idComentario, email), HttpStatus.OK);
	}
}