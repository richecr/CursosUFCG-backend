package com.cursosufcg.services;

import org.springframework.stereotype.Service;

import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class UsuarioService {

	private final UsuarioDAO usuarioDAO;
	
	public UsuarioService(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public Usuario create(Usuario usuario) {
		Usuario u = this.usuarioDAO.findByEmail(usuario.getEmail());
		
		if (u != null) {
			throw new RuntimeException("Usuário com esse email já existe!");
		}
		
		return this.usuarioDAO.save(usuario);
	}

	public Usuario findByEmail(String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		
		if (u == null) {
			throw new RuntimeException("Usuário não existe!");
		}
		
		return u;
	}
}
