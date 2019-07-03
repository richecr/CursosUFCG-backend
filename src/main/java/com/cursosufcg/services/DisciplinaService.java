package com.cursosufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.exceptions.disciplina.DisciplinaIncorreta;
import com.cursosufcg.exceptions.disciplina.DisciplinaNotFound;
import com.cursosufcg.models.Disciplina;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.repository.DisciplinaDAO;

@Service
public class DisciplinaService {

	private final DisciplinaDAO disciplinaDAO;
	
	@Autowired
	private PerfilService perfilService;
	
	public DisciplinaService(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}
	
	public Disciplina cadastrarDisciplina(Disciplina disciplina) {
		if ("".equals(disciplina.getNome()) || disciplina.getNome() == null) {
			throw new DisciplinaIncorreta("Nome da disciplina não pode ser vazio!");
		}

		return this.disciplinaDAO.save(disciplina);
	}
	
	public List<Disciplina> cadastrarVariasDisciplinas(List<Disciplina> disciplinas) {
		List<Disciplina> disciplinasSaida = this.disciplinaDAO.saveAll(disciplinas);
		for (Disciplina disciplina : disciplinas) {
			this.perfilService.cadastrarPerfil(disciplina.getId(), new Perfil());
		}

		return disciplinasSaida;
	}
	
	public Disciplina buscarPeloId(long id) {
		Disciplina disciplina = this.disciplinaDAO.findById(id);
		if (disciplina == null) {
			throw new DisciplinaNotFound("Disciplina não existe!");
		}

		return disciplina;
	}
	
	public List<Disciplina> buscarTodas() {
		return this.disciplinaDAO.findAll();
	}
	
	public List<Disciplina> buscarPorQuery(String query) {
		return this.disciplinaDAO.findBy(query);
	}
	
	public void deletarPeloId(long id) {
		Disciplina disciplina = this.disciplinaDAO.findById(id);
		if (disciplina == null) {
			throw new DisciplinaNotFound("Disciplina não existe!");
		}
	}
}
