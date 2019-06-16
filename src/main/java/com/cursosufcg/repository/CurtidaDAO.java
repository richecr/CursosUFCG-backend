package com.cursosufcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursosufcg.models.Curtida;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;

@Repository
public interface CurtidaDAO extends JpaRepository<Curtida, Long> {

	public List<Curtida> findByUsuario(Usuario usuario);
	
	public List<Curtida> findByPerfil(Perfil perfil);
	
}
