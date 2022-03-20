package com.pfe.back.BackPfe.entities;

import java.awt.List;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "Formation")
@Entity
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Titre")
	private String titre;
	
	@Column(name = "Date_Creation")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date dateCreation;
	
	@ManyToOne
	private User auteur;
	
	@OneToMany (mappedBy="formation", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<Cours> listeCours;
	
	
	
	public Collection<Cours> getListeCours() {
		return listeCours;
	}


	@JsonIgnore
	public void setListeCours(Collection<Cours> listeCours) {
		this.listeCours = listeCours;
	}





	public Formation(long id, String titre, Date dateCreation, User auteur, Collection<Cours> listeCours) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateCreation = dateCreation;
		this.auteur = auteur;
		this.listeCours = listeCours;
	}


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public Date getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}



	public User getAuteur() {
		return auteur;
	}



	public void setAuteur(User auteur) {
		this.auteur = auteur;
	}



	public Formation() {
		// TODO Auto-generated constructor stub
	}

}
