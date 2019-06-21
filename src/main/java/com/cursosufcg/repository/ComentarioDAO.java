package com.cursosufcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cursosufcg.models.Comentario;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;

public interface ComentarioDAO extends JpaRepository<Comentario, Long> {

	public List<Comentario> findByUsuario(Usuario usuario);

	public List<Comentario> findByPerfil(Perfil perfil);

	public Comentario findById(long id);

	@Query("SELECT c FROM Comentario c WHERE c.usuario = :usuario and c.perfil = :perfil")
	public Comentario findByUsuarioPerfil(@Param("perfil") Perfil perfil, @Param("usuario") Usuario usuario);

	@Query("SELECT c FROM Comentario c WHERE c.perfil = :perfil and c.apagado = false")
	public List<Comentario> findComentariosNaoApagadosByPerfil(@Param("perfil") Perfil perfil);

	@Query("SELECT c.respostas FROM Comentario c WHERE c = :comentario")
	public List<Comentario> getRespostasComentario(@Param("comentario") Comentario comentario);
	
}
