package com.cursosufcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursosufcg.models.Nota;
import com.cursosufcg.models.Perfil;

@Repository
public interface NotaDAO extends JpaRepository<Nota, Long>{
	
	public List<Nota> findByPerfil(Perfil perfil);
	
	public Nota findById(long id);
	
}
