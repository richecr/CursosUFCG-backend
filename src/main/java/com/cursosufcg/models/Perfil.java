package com.cursosufcg.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.*;

@Entity
public class Perfil {

	@Id
	private long id;

	@OneToOne
	private Disciplina disciplina;

	@OneToMany
	private List<Comentario> comentarios;

	@ManyToMany
	@JoinTable(name="perfil_curtidas", joinColumns=
	{@JoinColumn(name="perfil_id")}, inverseJoinColumns=
  	{@JoinColumn(name="usuario_email")})
	private List<Usuario> curtidas;

	@Transient
	private boolean usuarioAutenticadoCurtiu;
	
	public Perfil() {
	}

	public Perfil(long id, Disciplina disciplina, List<Comentario> comentarios, List<Usuario> curtidas) {
		super();
		this.disciplina = disciplina;
		this.comentarios = comentarios;
		this.curtidas = curtidas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Usuario> getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(List<Usuario> curtidas) {
		this.curtidas = curtidas;
	}

	public boolean isUsuarioAutenticadoCurtiu() {
		return usuarioAutenticadoCurtiu;
	}

	public void setUsuarioAutenticadoCurtiu(boolean usuarioAutenticadoCurtiu) {
		this.usuarioAutenticadoCurtiu = usuarioAutenticadoCurtiu;
	}
}
