package com.cursosufcg.ordenation;

import java.util.Comparator;

import com.cursosufcg.models.Perfil;

public class OrdenaPerfilPorLikes implements Comparator<Perfil> {

	@Override
	public int compare(Perfil o1, Perfil o2) {
		if (o1.getCurtidas().size() > o2.getCurtidas().size()) {
			return -1;
		} else {
			return 1;
		}
	}
}