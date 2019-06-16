package com.cursosufcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursosufcg.models.Comentario;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;

public interface ComentarioDAO extends JpaRepository<Comentario, Long> {

	public List<Comentario> findByUsuario(Usuario usuario);
	
	public List<Comentario> findByPerfil(Perfil perfil);
	
	public Comentario findById(long id);
	
}
