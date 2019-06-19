package com.cursosufcg.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Nota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	@JsonBackReference
	private Perfil perfil;
	
	private double nota;

	public Nota() {
	}

	public Nota(Usuario usuario, Perfil perfil, double nota) {
		super();
		this.usuario = usuario;
		this.perfil = perfil;
		this.nota = nota;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
}
