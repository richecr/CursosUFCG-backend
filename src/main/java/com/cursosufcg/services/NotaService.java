package com.cursosufcg.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.exceptions.usuario.UsuarioNaoEncontradoException;
import com.cursosufcg.models.Nota;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.NotaDAO;
import com.cursosufcg.repository.PerfilDAO;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class NotaService {

	@Autowired
	private NotaDAO notaDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	public Nota cadastrarNota(long id, String email, Nota nota) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);

		if (u == null) {
			throw new UsuarioNaoEncontradoException("Usuário não cadastrado");
		}

		if (p == null) {
			throw new RuntimeException("Perfil não existe");
		}

		Nota n = this.notaDAO.findByUsuarioPerfil(u, p);
		if (n != null) {
			n.setNota(nota.getNota());

			return this.notaDAO.save(n);
		} else {
			nota.setPerfil(p);
			nota.setUsuario(u);

			return this.notaDAO.save(nota);
		}
	}
	
	public double calculaMedia(Perfil perfil) {
		List<Nota> notas = this.notaDAO.findByPerfil(perfil);
		double soma = 0;
		double media = 0;
		if (!notas.isEmpty()) {
			for (Nota nota : notas) {
				soma += nota.getNota();
			}
			media = soma / (notas.size());	
		}

		return media;
	}
	
	public List<Nota> getAllNotas(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return this.notaDAO.findByPerfil(p);
	}
}
