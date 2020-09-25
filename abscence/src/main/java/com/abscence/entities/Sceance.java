package com.abscence.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Sceance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date date;
	private LocalTime debut;
	private LocalTime fin;
	private String type;
	
	@ManyToOne()

	private Cours cours;
	
	@ManyToOne()
	
	private Utilisateur utilisateur;

	
	public Sceance(Long id, Date date, LocalTime debut, LocalTime fin, String type, Cours cours,
			Utilisateur utilisateur) {
		super();
		this.id = id;
		this.date = date;
		this.debut = debut;
		this.fin = fin;
		this.type = type;
		this.cours = cours;
		this.utilisateur = utilisateur;
	}

	public Sceance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalTime getDebut() {
		return debut;
	}

	public void setDebut(LocalTime debut) {
		this.debut = debut;
	}

	public LocalTime getFin() {
		return fin;
	}

	public void setFin(LocalTime fin) {
		this.fin = fin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Sceance [id=" + id + ", date=" + date + ", debut=" + debut + ", fin=" + fin + ", type=" + type
				+ ", cours=" + cours + ", utilisateur=" + utilisateur + "]";
	}

	
}
