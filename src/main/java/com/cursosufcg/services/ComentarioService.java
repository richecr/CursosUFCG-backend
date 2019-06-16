package com.cursosufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Comentario create(long id, String email, Comentario comentario) {
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
		
		return this.comentarioDAO.save(comentario);
	}
	
	public List<Comentario> findByUsuario(String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		if (u == null) {
			throw new RuntimeException("Usuario não existe!");
		}
		
		return this.comentarioDAO.findByUsuario(u);
	}
	
	public List<Comentario> findByPerfil(long id) {
		Perfil p = this.perfilDAO.findById(id);
		if (p == null) {
			throw new RuntimeException("Perfil não existe!");
		}
		
		return this.comentarioDAO.findByPerfil(p);
	}
	
	public Comentario createResposta(long id, Comentario comentario) {
		Comentario c = this.comentarioDAO.findById(id);
		if (c == null) {
			throw new RuntimeException("Comentário não existe!");
		}
		c.getRespostas().add(comentario);

		this.comentarioDAO.deleteById(id);
		return this.comentarioDAO.save(c);
	}
}
