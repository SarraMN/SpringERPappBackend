package com.pfe.back.BackPfe.entities;

import java.awt.List;
import java.util.Collection;
import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@Column(name = "Date_Modification")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date dateMdf;
	
	@ManyToOne
	private User auteur;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@Column(columnDefinition="TEXT")
	private String info;
	
	@OneToMany (mappedBy="formation", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<Cours> listeCours;
	
	
	private String categorie;
	
	private float prix;
	
	private int nbrCours;
	
	private String etat;


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public Formation(long id, String titre, Date dateCreation, Date dateMdf, User auteur, String description,
			String info, Collection<Cours> listeCours, String categorie, float prix, int nbrCours, String etat) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateCreation = dateCreation;
		this.dateMdf = dateMdf;
		this.auteur = auteur;
		this.description = description;
		this.info = info;
		this.listeCours = listeCours;
		this.categorie = categorie;
		this.prix = prix;
		this.nbrCours = nbrCours;
		this.etat = etat;
	}


	@JsonIgnore
	public Collection<Cours> getListeCours() {
		return listeCours;
	}


	@JsonIgnore
	public void setListeCours(Collection<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	

	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public int getNbrCours() {
		return nbrCours;
	}


	public void setNbrCours(int nbrCours) {
		this.nbrCours = nbrCours;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
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


	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDateCreation() {
		return dateCreation;
	}


	@JsonFormat(pattern = "yyyy-MM-dd")
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDateMdf() {
		return dateMdf;
	}

    
	public void setDateMdf(Date dateMdf) {
		this.dateMdf = dateMdf;
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
