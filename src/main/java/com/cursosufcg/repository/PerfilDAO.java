package com.cursosufcg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursosufcg.models.Perfil;

@Repository
public interface PerfilDAO extends JpaRepository<Perfil, Long>{

	public Perfil findById(long id);
	
}
