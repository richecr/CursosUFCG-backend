package com.cursosufcg.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	private List<Comentario> comentarios;
	
	@ManyToMany(mappedBy = "curtidas")
	@JsonBackReference
	private List<Perfil> curtidas;
	
	@OneToMany
	private List<Nota> notasDePerfilsAvaliados;

	public Usuario() {
	}
	
	public Usuario(String email, String primeiroNome, String ultimoNome, String senha, List<Comentario> comentarios, List<Perfil> curtidas,
			  	   List<Nota> notasDePerfilsAvaliados) {
		super();
		this.email = email;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.senha = senha;
		this.comentarios = comentarios;
		this.curtidas = curtidas;
		this.notasDePerfilsAvaliados = notasDePerfilsAvaliados;
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

	public List<Perfil> getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(List<Perfil> curtidas) {
		this.curtidas = curtidas;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Nota> getNotasDePerfilsAvaliados() {
		return notasDePerfilsAvaliados;
	}

	public void setNotasDePerfilsAvaliados(List<Nota> notasDePerfilsAvaliados) {
		this.notasDePerfilsAvaliados = notasDePerfilsAvaliados;
	}
}
