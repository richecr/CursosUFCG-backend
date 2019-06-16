package com.cursosufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.models.Comentario;
import com.cursosufcg.models.Curtida;
import com.cursosufcg.models.Disciplina;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.ComentarioDAO;
import com.cursosufcg.repository.CurtidaDAO;
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
	private CurtidaDAO curtidaDAO;
	
	@Autowired
	private ComentarioDAO comentarioDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Perfil create(long id, Perfil perfil) {
		Disciplina d = this.disciplinaDAO.findById(id);
		perfil.setDisciplina(d);
		
		return this.perfilDAO.save(perfil);
	}

	public Perfil findById(long id) {
		Perfil p = this.perfilDAO.findById(id);
		p.setCurtidas(this.getAllCurtidas(id));
		p.setComentarios(this.getAllComentarios(id));

		return p;
	}

	public List<Perfil> findAll() {
		return this.perfilDAO.findAll();
	}
	
	public Curtida createCurtida(long id, String email, Curtida curtida) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		curtida.setUsuario(u);
		curtida.setPerfil(p);

		return this.curtidaDAO.save(curtida);
	}
	
	public List<Curtida> getAllCurtidas(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return this.curtidaDAO.findByPerfil(p);
	}
	
	public Comentario createComentario(long id, String email, Comentario comentario) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		comentario.setUsuario(u);
		comentario.setPerfil(p);
		// TODO Pegar a data e hora atual.

		return this.comentarioDAO.save(comentario);
	}
	
	public List<Comentario> getAllComentarios(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return this.comentarioDAO.findByPerfil(p);
	}
}
