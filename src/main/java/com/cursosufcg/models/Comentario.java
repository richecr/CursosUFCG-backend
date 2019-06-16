package com.cursosufcg.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne
	@JsonBackReference(value = "usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JsonBackReference(value = "perfil")
	private Perfil perfil;
	
	private String conteudo;
	
	private Date date;
	
	@OneToMany
	@JsonBackReference(value = "respostas")
	private List<Comentario> respostas;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", updatable = false, insertable = false)
	private Comentario respostasPai;
	
	public Comentario() {
	}

	public Comentario(Usuario usuario, Perfil perfil, Date date, List<Comentario> respostas, Comentario respostasPai) {
		super();
		this.usuario = usuario;
		this.perfil = perfil;
		this.date = date;
		this.respostas = respostas;
		this.respostasPai = respostasPai;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Comentario> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Comentario> respostas) {
		this.respostas = respostas;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Comentario getRespostasPai() {
		return respostasPai;
	}

	public void setRespostasPai(Comentario respostasPai) {
		this.respostasPai = respostasPai;
	}
}
