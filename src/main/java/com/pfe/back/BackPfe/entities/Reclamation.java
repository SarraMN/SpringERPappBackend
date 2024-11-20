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

@Table(name = "reclamation")
@Entity
public class Reclamation {
	
	@Id
	@Column(name ="reference")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	@Column(columnDefinition="TEXT")
	private String contenu;	
	
	
	@Column(name = "objet")
	private String objet;
	
	@Column(name = "traitee")
	private Boolean traitee;
	
	@Column(name = "Date_de_traitement")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date datetraitement;
	
	@Column(name = "Date_Envoie")
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date dateenvoie;
	
	@Column(columnDefinition="TEXT")
	private String reponse;
	
	@ManyToOne
	private User candidat;
	
	@ManyToOne() 
	private FileDB piececand;
	
	@ManyToOne() 
	private FileDB pieceadmin;


	

	public Reclamation(long id, String contenu, String objet, Boolean traitee, Date datetraitement, Date dateenvoie,
			String reponse, User candidat, FileDB piececand, FileDB pieceadmin) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.objet = objet;
		this.traitee = traitee;
		this.datetraitement = datetraitement;
		this.dateenvoie = dateenvoie;
		this.reponse = reponse;
		this.candidat = candidat;
		this.piececand = piececand;
		this.pieceadmin = pieceadmin;
	}


	public FileDB getPieceadmin() {
		return pieceadmin;
	}


	public void setPieceadmin(FileDB pieceadmin) {
		this.pieceadmin = pieceadmin;
	}


	public Reclamation() {
	}


	public long getId() {
		return id;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	public Boolean getTraitee() {
		return traitee;
	}

	public void setTraitee(Boolean traitee) {
		this.traitee = traitee;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDatetraitement() {
		return datetraitement;
	}

	public void setDatetraitement(Date datetraitement) {
		this.datetraitement = datetraitement;
	}
     
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDateenvoie() {
		return dateenvoie;
	}


	public void setDateenvoie(Date dateenvoie) {
		this.dateenvoie = dateenvoie;
	}


	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public User getCandidat() {
		return candidat;
	}

	public void setCandidat(User candidat) {
		this.candidat = candidat;
	}

	public FileDB getPiececand() {
		return piececand;
	}

	public void setPiececand(FileDB piececand) {
		this.piececand = piececand;
	}


	
}