package com.cursosufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.models.Curtida;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.CurtidaDAO;
import com.cursosufcg.repository.PerfilDAO;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class CurtidaService {

	@Autowired
	private CurtidaDAO curtidaDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	public Curtida create(long idPerfil, String email, Curtida curtida) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(idPerfil);
		curtida.setUsuario(u);
		curtida.setPerfil(p);
		
		return this.curtidaDAO.save(curtida);
	}

	public List<Curtida> findByUsuario(String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		if (u == null) {
			throw new RuntimeException("Usuario não existe!");
		}
		
		return this.curtidaDAO.findByUsuario(u);
	}
	
	public List<Curtida> findByPerfil(long id) {
		Perfil p = this.perfilDAO.findById(id);
		if (p == null) {
			throw new RuntimeException("Perfil não existe!");
		}
		
		return this.curtidaDAO.findByPerfil(p);
	}
}
