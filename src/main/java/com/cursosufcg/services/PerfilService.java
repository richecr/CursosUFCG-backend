package com.cursosufcg.services;

import java.util.List;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.exceptions.usuario.UsuarioNaoEncontradoException;
import com.cursosufcg.models.Comentario;

import com.cursosufcg.models.Disciplina;
import com.cursosufcg.models.Nota;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.ordenation.OrdenaPerfilPorLikes;
import com.cursosufcg.repository.ComentarioDAO;
import com.cursosufcg.repository.DisciplinaDAO;
import com.cursosufcg.repository.NotaDAO;
import com.cursosufcg.repository.PerfilDAO;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class PerfilService {

	@Autowired
	private PerfilDAO perfilDAO;

	@Autowired
	private DisciplinaDAO disciplinaDAO;
	
	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private NotaService notaService;

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Perfil cadastrarPerfil(long id, Perfil perfil) {
		Perfil p = this.perfilDAO.findById(id);
		if (p != null) {
			throw new RuntimeException("Perfil para essa disciplina já existe!");
		}
		Disciplina d = this.disciplinaDAO.findById(id);
		perfil.setId(id);
		perfil.setDisciplina(d);

		return this.perfilDAO.save(perfil);
	}
	
	public List<Perfil> buscarTodos() {
		return this.perfilDAO.findAll();
	}

	public Perfil procurarPorId(long id, String email) {
		Perfil p = this.perfilDAO.findById(id);
		Usuario u = this.usuarioDAO.findByEmail(email);
		if (p == null) {
			throw new RuntimeException("Perfil para essa disciplina não existe!");
		}
		if (u == null) {
			throw new RuntimeException("Usuário não existe!");
		}

		p.setComentarios(this.comentarioService.getAllComentarios(p, u));
		this.verificaUsuarioCurtiu(p, u);
	 	p.setMedia(this.notaService.calculaMedia(p));

		return this.perfilDAO.save(p);
	}

	public Perfil curtirPerfil(long id, String email) {
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
	
	private void verificaUsuarioCurtiu(Perfil perfil, Usuario usuario) {
		if (perfil.getCurtidas().contains(usuario)) {
			perfil.setUsuarioAutenticadoCurtiu(true);
		} else {
			perfil.setUsuarioAutenticadoCurtiu(false);
		}
	}

	public List<Perfil> findPerfilOrderedByLikes() {
		List<Perfil> perfils = this.perfilDAO.findAll();

		if (perfils == null) {
			throw new RuntimeException("Perfil para essa disciplina não existe!");
		}
		Collections.sort(perfils, new OrdenaPerfilPorLikes());

		return perfils;
	}
}