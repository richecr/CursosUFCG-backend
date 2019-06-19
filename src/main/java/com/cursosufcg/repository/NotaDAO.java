package com.cursosufcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cursosufcg.models.Nota;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;

@Repository
public interface NotaDAO extends JpaRepository<Nota, Long>{
	
	public List<Nota> findByUsuario(Usuario usuario);
	
	@Query("SELECT n FROM Nota n WHERE n.usuario = :usuario and n.perfil = :perfil")
	public Nota findByUsuarioPerfil(@Param("usuario") Usuario usuario, @Param("perfil") Perfil perfil);
	
	public List<Nota> findByPerfil(Perfil perfil);
	
	public Nota findById(long id);
	
}
