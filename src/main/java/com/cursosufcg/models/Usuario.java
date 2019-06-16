package com.cursosufcg.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Usuario {

	@Id
	private String email;
	private String primeiroNome;
	private String ultimoNome;
	private String senha;
	
	@OneToMany
	@JsonBackReference(value = "curtidas")
	private List<Curtida> curtidas;
	
	@OneToMany
	@JsonBackReference(value = "comentarios")
	private List<Comentario> comentarios;

	public Usuario(String email, String primeiroNome, String ultimoNome, String senha, List<Curtida> curtidas, List<Comentario> comentarios) {
		super();
		this.email = email;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.senha = senha;
		this.curtidas = curtidas;
		this.comentarios = comentarios;
	}
	
	public Usuario() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Curtida> getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(List<Curtida> curtidas) {
		this.curtidas = curtidas;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
