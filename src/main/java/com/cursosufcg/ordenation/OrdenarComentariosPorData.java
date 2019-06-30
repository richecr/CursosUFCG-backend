package com.cursosufcg.ordenation;

import java.util.Comparator;

import com.cursosufcg.models.Comentario;
import com.cursosufcg.models.Perfil;

public class OrdenarComentariosPorData implements Comparator<Comentario>{

	@Override
	public int compare(Comentario o1, Comentario o2) {
		return o2.getDate().compareTo(o1.getDate());
	}

}
