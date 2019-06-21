package com.cursosufcg.services;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursosufcg.exceptions.usuario.UsuarioNaoEncontradoException;
import com.cursosufcg.models.Comentario;

import com.cursosufcg.models.Disciplina;
import com.cursosufcg.models.Nota;
import com.cursosufcg.models.Perfil;
import com.cursosufcg.models.Usuario;
import com.cursosufcg.repository.ComentarioDAO;
import com.cursosufcg.repository.DisciplinaDAO;
import com.cursosufcg.repository.NotaDAO;
import com.cursosufcg.repository.PerfilDAO;
import com.cursosufcg.repository.UsuarioDAO;

@Service
public class PerfilService {

	@Autowired
	private PerfilDAO perfilDAO;

	@Autowired
	private DisciplinaDAO disciplinaDAO;

	@Autowired
	private ComentarioDAO comentarioDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private NotaDAO notaDAO;

	public Perfil create(long id, Perfil perfil) {
		Perfil p = this.perfilDAO.findById(id);
		if (p != null) {
			throw new RuntimeException("Perfil para essa disciplina já existe!");
		}
		Disciplina d = this.disciplinaDAO.findById(id);
		perfil.setId(id);
		perfil.setDisciplina(d);

		return this.perfilDAO.save(perfil);
	}
	
	public List<Perfil> findAll() {
		return this.perfilDAO.findAll();
	}

	public Perfil findById(long id, String email) {
		Perfil p = this.perfilDAO.findById(id);
		if (p == null) {
			throw new RuntimeException("Perfil para essa disciplina não existe!");
		}
		Usuario u = this.usuarioDAO.findByEmail(email);
		p.setComentarios(this.getAllComentarios(id));
		p.setNotasDeUsuarios(this.getAllNotas(id));
		
		this.verificaComentarioDoUsuario(p, u);
		this.verificaUsuarioCurtiu(p, u);
		this.calculaMedia(p);
		
		return p;
	}

	public Perfil createCurtida(long id, String email) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		if (p.getCurtidas().contains(u)) {
			p.getCurtidas().remove(u);
		} else {
			p.getCurtidas().add(u);
		}

		return this.perfilDAO.save(p);
	}

	public Comentario createComentario(long id, String email, Comentario comentario) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		if (u == null) {
			throw new UsuarioNaoEncontradoException("Usuário não cadastrado");
		}

		if (p == null) {
			throw new RuntimeException("Perfil não existe");
		}
		
		comentario.setUsuario(u);
		comentario.setPerfil(p);
		comentario.setDate(new Date());
		
		return this.comentarioDAO.save(comentario);
	}
	
	public Comentario createResponderComentario(long idComentario, String email, Comentario comentario) {
		Comentario c = this.comentarioDAO.findById(idComentario);
		Usuario usuario = this.usuarioDAO.findByEmail(email);

		c.getRespostas().add(comentario);
		comentario.setUsuario(usuario);
		comentario.setRespostasPai(c);
		comentario.setDate(new Date());
		
		return this.comentarioDAO.save(comentario);
	}
	
	public Nota createNota(long id, String email, Nota nota) {
		Usuario u = this.usuarioDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);

		if (u == null) {
			throw new UsuarioNaoEncontradoException("Usuário não cadastrado");
		}

		if (p == null) {
			throw new RuntimeException("Perfil não existe");
		}

		Nota n = this.notaDAO.findByUsuarioPerfil(u, p);
		if (n != null) {
			n.setNota(nota.getNota());

			return this.notaDAO.save(n);
		} else {
			nota.setPerfil(p);
			nota.setUsuario(u);

			return this.notaDAO.save(nota);
		}
	}

	public List<Usuario> getAllCurtidas(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return p.getCurtidas();
	}

	public List<Comentario> getAllComentarios(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return this.comentarioDAO.findComentariosNaoApagadosByPerfil(p);
	}
	
	private List<Nota> getAllNotas(long id) {
		Perfil p = this.perfilDAO.findById(id);
		
		return this.notaDAO.findByPerfil(p);
	}
	
	private void verificaUsuarioCurtiu(Perfil perfil, Usuario usuario) {
		if (perfil.getCurtidas().contains(usuario)) {
			perfil.setUsuarioAutenticadoCurtiu(true);
		} else {
			perfil.setUsuarioAutenticadoCurtiu(false);
		}
	}

	private void verificaComentarioDoUsuario(Perfil perfil, Usuario usuario) {
		List<Comentario> comentarios = perfil.getComentarios();
		for (Comentario comentario : comentarios) {
			if (comentario.getUsuario().equals(usuario)) {
				comentario.setComentarioDoUsuarioAutenticado(true);
			} else {
				comentario.setComentarioDoUsuarioAutenticado(false);
			}
		}
	}
	
	private void calculaMedia(Perfil perfil) {
		List<Nota> notas = perfil.getNotasDeUsuarios();
		double soma = 0;
		double media = 0;
		if (!notas.isEmpty()) {
			for (Nota nota : notas) {
				soma += nota.getNota();
			}
			media = soma / (notas.size());	
			perfil.setMedia(media);
		}
	}

	public Comentario apagaComentario(long idPerfil, long idComentario, String email) {
		Perfil perfil = this.perfilDAO.findById(idPerfil);
		Usuario usuario = this.usuarioDAO.findByEmail(email);
		
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("Usuário não cadastrado");
		}

		if (perfil == null) {
			throw new RuntimeException("Perfil não existe");
		}

		Comentario comentario = this.comentarioDAO.findById(idComentario);
		if (comentario == null) {
			throw new RuntimeException("Comentário não existe!");
		}

		comentario.setApagado(true);
		return this.comentarioDAO.save(comentario);
	}

	public Comentario getComentario(long idComentario) {
		return this.comentarioDAO.findById(idComentario);
	}
}