package com.abscence.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nom;
	
	@ManyToMany(mappedBy = "cours")
	@JsonIgnore
	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	
	@OneToMany(mappedBy = "cours")	
	@JsonIgnore
	private Collection<Sceance> sceances =  new ArrayList<Sceance>();

	public Cours(Long id, String nom, List<Utilisateur> utilisateurs, Collection<Sceance> sceances) {
		super();
		this.id = id;
		this.nom = nom;
		this.utilisateurs = utilisateurs;
		this.sceances = sceances;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@JsonIgnore
	public Collection<Sceance> getSceances() {
		return sceances;
	}

	public void setSceances(Collection<Sceance> sceances) {
		this.sceances = sceances;
	}

	public Cours() {
		super();
	}

	@Override
	public String toString() {
		return "Cours [id=" + id + ", nom=" + nom + ", utilisateurs=" + utilisateurs + ", sceances=" + sceances + "]";
	}

	
	
	
}
