package com.abscence.entities;

import java.util.List;

public class utilisateursPage {

	public List<Utilisateur> utilisateurs;
	public int totalPages;
	public utilisateursPage(List<Utilisateur> utilisateurs, int totalPages) {
		super();
		this.utilisateurs = utilisateurs;
		this.totalPages = totalPages;
	}
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	@Override
	public String toString() {
		return "utilisateursPage [utilisateurs=" + utilisateurs + ", totalPages=" + totalPages + "]";
	}
	public utilisateursPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
