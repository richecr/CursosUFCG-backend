package com.cursosufcg.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cursosufcg.models.Disciplina;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {

	
	public Disciplina save(Disciplina disciplina);
	
	public void deleteById(long id);
	
	public Disciplina findById(long id);
	
	@Query("SELECT d FROM Disciplina d WHERE d.nome LIKE %:pquery%")
	public List<Disciplina> findBy(@Param("pquery") String query);
	
	public List<Disciplina> findAll();
}
