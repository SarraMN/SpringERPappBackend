package com.pfe.back.BackPfe.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Table(name = "Cours")
@Entity
public class Cours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Titre")
	private String titre;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@Column(name = "Date_Creation")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date dateCreation;
	
	@Column(name = "Date_Modification")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date dateMdf;
	
	@Column(columnDefinition="TEXT")
	private String objectif;
	
    
	private String etat;

	
	public Cours(long id, String titre, String description, Date dateCreation, Date dateMdf, String objectif,
			String etat, Formation formation) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateCreation = dateCreation;
		this.dateMdf = dateMdf;
		this.objectif = objectif;
		this.etat = etat;
		this.formation = formation;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	@ManyToOne
	private Formation formation;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDateMdf() {
		return dateMdf;
	}

	public void setDateMdf(Date dateMdf) {
		this.dateMdf = dateMdf;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Cours() {
		// TODO Auto-generated constructor stub
	}

}
