package com.cursosufcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;

@Repository
public interface PerfilDAO extends JpaRepository<Perfil, Long>{

	public Perfil findById(long id);
	
	public Usuario findByCurtidas(Usuario usuario);

	@Query("SELECT p FROM Perfil p WHERE p.disciplina.nome LIKE %:pquery%")
	public List<Perfil> findByQuery(@Param("pquery") String query);

	
}
