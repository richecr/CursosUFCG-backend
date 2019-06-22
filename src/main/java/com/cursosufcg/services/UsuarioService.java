package com.cursosufcg.services;

import org.springframework.stereotype.Service;

import com.cursosufcg.exceptions.usuario.UsuarioJaExisteException;
import com.cursosufcg.exceptions.usuario.UsuarioNaoEncontradoException;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class UsuarioService {

	private final UsuarioDAO usuarioDAO;
	
	public UsuarioService(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		Usuario u = this.usuarioDAO.findByEmail(usuario.getEmail());
		
		if (u != null) {
			throw new UsuarioJaExisteException("Usuário com esse email já existe!");
		}
		
		return this.usuarioDAO.save(usuario);
	}

	public Usuario buscarPorEmail(String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		
		if (u == null) {
			throw new UsuarioNaoEncontradoException("Usuário não existe!");
		}
		
		return u;
	}

	public void deletarPeloId(String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		
		if (u == null) {
			throw new UsuarioNaoEncontradoException("Usuário não existe!");
		}
		
		this.usuarioDAO.deleteById(email);
	}
}
