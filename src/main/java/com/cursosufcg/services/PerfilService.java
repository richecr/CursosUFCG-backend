package com.cursosufcg.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.exceptions.usuario.UsuarioNaoEncontradoException;
import com.cursosufcg.models.Comentario;

import com.cursosufcg.models.Disciplina;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.ComentarioDAO;
import com.cursosufcg.repository.DisciplinaDAO;
import com.cursosufcg.repository.PerfilDAO;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class PerfilService {

	@Autowired
	private PerfilDAO perfilDAO;
	
	@Autowired
	private DisciplinaDAO disciplinaDAO;
	
	@Autowired
	private ComentarioDAO comentarioDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Perfil create(long id, Perfil perfil) {
		Perfil p = this.perfilDAO.findById(id);
		if (p != null) {
			throw new RuntimeException("Perfil para essa disciplina já existe!");
		}
		Disciplina d = this.disciplinaDAO.findById(id);
		perfil.setId(id);
		perfil.setDisciplina(d);

		return this.perfilDAO.save(perfil);
	}

	public Perfil findById(long id, String email) {
		Perfil p = this.perfilDAO.findById(id);
		if (p == null) {
			throw new RuntimeException("Perfil para essa disciplina não existe!");
		}
		Usuario u = this.usuarioDAO.findByEmail(email);
		p.setComentarios(this.getAllComentarios(id));
		this.verificaComentarioDoUsuario(p, u);
		this.verificaUsuarioCurtiu(p, u);

		return p;
	}
	
	private void verificaUsuarioCurtiu(Perfil perfil, Usuario usuario) {
		if (perfil.getCurtidas().contains(usuario)) {
			perfil.setUsuarioAutenticadoCurtiu(true);
		} else {
			perfil.setUsuarioAutenticadoCurtiu(false);
		}
	}
	
	private void verificaComentarioDoUsuario(Perfil perfil, Usuario usuario) {
		List<Comentario> comentarios = perfil.getComentarios();
		for (Comentario comentario : comentarios) {
			if (comentario.getUsuario().equals(usuario)) {
				comentario.setComentarioDoUsuarioAutenticado(true);
			} else {
				comentario.setComentarioDoUsuarioAutenticado(false);
			}
		}
	}
	
	public List<Perfil> findAll() {
		return this.perfilDAO.findAll();
	}
	
	public Perfil createCurtida(long id, String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		if (p.getCurtidas().contains(u)) {
			p.getCurtidas().remove(u);
		} else {
			p.getCurtidas().add(u);
		}

		return this.perfilDAO.save(p);
	}
	
	public List<Usuario> getAllCurtidas(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return p.getCurtidas();
	}
	
	public Comentario createComentario(long id, String email, Comentario comentario) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		if (u == null) {
			throw new UsuarioNaoEncontradoException("Usuário não cadastrado");
		}

		if (p == null) {
			throw new RuntimeException("Perfil não existe");
		}
		
		comentario.setUsuario(u);
		comentario.setPerfil(p);
		comentario.setDate(new Date());
		
		return this.comentarioDAO.save(comentario);
	}
	
	public List<Comentario> getAllComentarios(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return this.comentarioDAO.findByPerfil(p);
	}
}
