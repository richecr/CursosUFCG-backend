package com.cursosufcg.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cursosufcg.models.Usuario;

@Repository
public interface UsuarioDAO<T, ID extends Serializable> extends JpaRepository<Usuario, String> {

	public Usuario save(Usuario usuario);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :plogin")
	public Usuario findByEmail(@Param("plogin") String email);
}
