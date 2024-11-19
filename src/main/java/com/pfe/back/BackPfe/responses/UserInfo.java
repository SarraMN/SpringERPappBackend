package  com.pfe.back.BackPfe.responses;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserInfo {
	private long id;
	private String nom;
	private String prenom;
	private String userName;
	@DateTimeFormat (pattern = "yyyy-mm-dd")
	private Date date_de_naissance;
	private String Genre;
	private String numero_de_telephone;
	//à corriger 
	private String adressse;
	private String etat_civil;
	private String email;
	private Object roles;
    private String password;
    @DateTimeFormat (pattern = "Yyyy-mm-dd")
    private Date createdAt;
    @DateTimeFormat (pattern = "Yyyy-mm-dd")
	private Date updatedAt;
	private Long  idimage;
    @DateTimeFormat (pattern = "Yyyy-mm-dd")
    private Date lastLogin;
    private int soldeLeaves;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDate_de_naissance() {
		return date_de_naissance;
	}

	public void setDate_de_naissance(Date date_de_naissance) {
		this.date_de_naissance = date_de_naissance;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getNumero_de_telephone() {
		return numero_de_telephone;
	}

	public void setNumero_de_telephone(String numero_de_telephone) {
		this.numero_de_telephone = numero_de_telephone;
	}

	public String getAdressse() {
		return adressse;
	}

	public void setAdressse(String adressse) {
		this.adressse = adressse;
	}

	public String getEtat_civil() {
		return etat_civil;
	}

	public void setEtat_civil(String etat_civil) {
		this.etat_civil = etat_civil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Object getRoles() {
		return roles;
	}

	public void setRoles(Object roles) {
		this.roles = roles;
	}	
	
	public Long getIdimage() {
		return idimage;
	}

	public void setIdimage(Long idimage) {
		this.idimage = idimage;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")

	public Date getLastLogin() {
		return lastLogin;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public int getSoldeLeaves() {
		return soldeLeaves;
	}

	public void setSoldeLeaves(int soldeLeaves) {
		this.soldeLeaves = soldeLeaves;
	}
	
}
