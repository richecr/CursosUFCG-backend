package com.cursosufcg.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cursosufcg.models.Disciplina;
import com.cursosufcg.repository.DisciplinaDAO;

@Service
public class DisciplinaService {

	private final DisciplinaDAO disciplinaDAO;
	
	public DisciplinaService(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}
	
	public Disciplina cadastrarDisciplina(Disciplina disciplina) {
		return this.disciplinaDAO.save(disciplina);
	}
	
	public List<Disciplina> cadastrarVariasDisciplinas(List<Disciplina> disciplinas) {
		return this.disciplinaDAO.saveAll(disciplinas);
	}
	
	public Disciplina buscarPeloId(long id) {
		return this.disciplinaDAO.findById(id);
	}
	
	public List<Disciplina> buscarTodas() {
		return this.disciplinaDAO.findAll();
	}
	
	public List<Disciplina> buscarPorQuery(String query) {
		return this.disciplinaDAO.findBy(query);
	}
	
	public void deletarPeloId(long id) {
		this.disciplinaDAO.deleteById(id);
	}
}
