package com.cursosufcg.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.exceptions.usuario.UsuarioNaoEncontradoException;
import com.cursosufcg.models.Comentario;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.ComentarioDAO;
import com.cursosufcg.repository.PerfilDAO;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioDAO comentarioDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	public Comentario comentar(long id, String email, Comentario comentario) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		if (u == null) {
			throw new RuntimeException("Usuario não existe!");
		}
		if (p == null) {
			throw new RuntimeException("Perfil não existe!");
		}
		comentario.setUsuario(u);
		comentario.setPerfil(p);
		comentario.setDate(new Date());

		return this.comentarioDAO.save(comentario);
	}
	
	public Comentario responderComentario(long idComentario, String email, Comentario comentario) {
		Comentario c = this.comentarioDAO.findById(idComentario);
		Usuario usuario = this.usuarioDAO.findByEmail(email);

		comentario.setUsuario(usuario);
		comentario.setComentarioPai(c);
		comentario.setDate(new Date());
		c.getRespostas().add(comentario);
		
		Comentario cN = this.comentarioDAO.save(comentario);
		this.comentarioDAO.save(c);

		return cN;
	}

	public void verificaComentarioDoUsuario(List<Comentario> comentarios, Usuario usuario) {
		for (Comentario comentario : comentarios) {
			if (comentario.getUsuario().equals(usuario)) {
				comentario.setComentarioDoUsuarioAutenticado(true);
			} else {
				comentario.setComentarioDoUsuarioAutenticado(false);
			}
			this.verificaRespostaDeComentariosDoUsuario(usuario, comentario);
		}
	}
	
	private void verificaRespostaDeComentariosDoUsuario(Usuario usuario, Comentario comentario) {
		for (Comentario resposta : comentario.getRespostas()) {
			if (resposta.getUsuario().equals(usuario)) {
				resposta.setComentarioDoUsuarioAutenticado(true);
			} else {
				resposta.setComentarioDoUsuarioAutenticado(false);
			}
		}
	}
	
	public Comentario apagarComentario(long idPerfil, long idComentario, String email) {
		Perfil perfil = this.perfilDAO.findById(idPerfil);
		Usuario usuario = this.usuarioDAO.findByEmail(email);
		
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("Usuário não cadastrado");
		}

		if (perfil == null) {
			throw new RuntimeException("Perfil não existe");
		}

		Comentario comentario = this.comentarioDAO.findById(idComentario);
		if (comentario == null) {
			throw new RuntimeException("Comentário não existe!");
		}
		
		if (!comentario.getUsuario().equals(usuario)) {
			throw new RuntimeException("Este Usuário não é dono do comentário!");
		}

		comentario.setApagado(true);
		return this.comentarioDAO.save(comentario);
	}

	public List<Comentario> buscarPorUsuario(String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		if (u == null) {
			throw new RuntimeException("Usuario não existe!");
		}
		
		return this.comentarioDAO.findByUsuario(u);
	}

	public List<Comentario> getAllComentarios(Perfil perfil, Usuario usuario) {
		List<Comentario> comentarios = this.comentarioDAO.findComentariosNaoApagadosByPerfil(perfil);
		for (Comentario comentario : comentarios) {
			List<Comentario> respostas = this.comentarioDAO.findRespostas(comentario);
			comentario.setRespostas(respostas);
		}
		this.verificaComentarioDoUsuario(comentarios, usuario);

		return comentarios;
	}
	
	public List<Comentario> getAllComentarios(Perfil perfil) {
		List<Comentario> comentarios = this.comentarioDAO.findComentariosNaoApagadosByPerfil(perfil);
		for (Comentario comentario : comentarios) {
			List<Comentario> respostas = this.comentarioDAO.findRespostas(comentario);
			comentario.setRespostas(respostas);
		}

		return comentarios;
	}
	
	public Comentario getComentario(long idComentario) {
		return this.comentarioDAO.findById(idComentario);
	}
}
