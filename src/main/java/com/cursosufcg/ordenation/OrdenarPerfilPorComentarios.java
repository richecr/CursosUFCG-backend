package com.cursosufcg.ordenation;

import java.util.Comparator;

import com.cursosufcg.models.Perfil;

public class OrdenarPerfilPorComentarios implements Comparator<Perfil> {

	@Override
	public int compare(Perfil o1, Perfil o2) {
		if (o1.getComentarios().size() > o2.getComentarios().size()) {
			return -1;
		} else {
			return 1;
		}
	}
}
