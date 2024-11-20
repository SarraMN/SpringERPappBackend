package com.pfe.back.BackPfe.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class actualite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "titre")
	private String titre;
	@Column(columnDefinition="TEXT")
	private String description;

	@Column(name = "dateCreation")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date datecreation;  
	@Column(name = "Date_Expiration")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date dateExpiration;
	private boolean archivee;
	@OneToOne() 
	private FileDB image;
	
	
	public actualite() {
		super();
	}
	public actualite(long id, String titre, String description, Date datecreation, Date dateExpiration,
			boolean archivee, FileDB image) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.datecreation = datecreation;
		this.dateExpiration = dateExpiration;
		this.archivee = archivee;
		this.image = image;
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
	public Date getDatecreation() {
		return datecreation;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDateExpiration() {
		return dateExpiration;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public boolean isArchivee() {
		return archivee;
	}
	public void setArchivee(boolean archivee) {
		this.archivee = archivee;
	}
	public FileDB getImage() {
		return image;
	}
	public void setImage(FileDB image) {
		this.image = image;
	}
	
}
