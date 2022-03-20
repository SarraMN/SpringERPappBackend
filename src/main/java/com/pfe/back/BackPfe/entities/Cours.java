package com.pfe.back.BackPfe.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Table(name = "Cours")
@Entity
public class Cours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Titre")
	private String titre;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "Date_Creation")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date dateCreation;
	
	
	@ManyToOne
	private Formation formation;
	
	public Cours(long id, String titre, String description, Date dateCreation, Formation formation) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateCreation = dateCreation;
		this.formation = formation;
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
