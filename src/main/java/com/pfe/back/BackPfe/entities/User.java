package  com.pfe.back.BackPfe.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "AUTH_USER_DETAILS")
@Entity
public class User implements UserDetails {
	

	

	public String getEtatCompte() {
		return etatCompte;
	}

	public void setEtatCompte(String etatCompte) {
		this.etatCompte = etatCompte;
	}

	public User(long id, String userName, String password, String nom, String prenom, String email,
			Date date_de_naissance, String numero_de_telephone, boolean enabled, String etat_civil, String genre,
			String adresse, String etatCompte, Authority authority, Date lastLogin, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date_de_naissance = date_de_naissance;
		this.numero_de_telephone = numero_de_telephone;
		this.enabled = enabled;
		this.etat_civil = etat_civil;
		this.genre = genre;
		this.adresse = adresse;
		this.etatCompte = etatCompte;
		this.authority = authority;
		this.lastLogin = lastLogin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public User() {
		
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "User_Name", unique = true)
	private String userName;

	@Column(name = "Mot_de_passe")
	private String password;

	@Column(name = "Nom")
	private String nom;

	@Column(name = "Prenom")
	private String prenom;

	@Column(name = "email")
	private String email;
	
	@DateTimeFormat (pattern = "Yyyy-mm-dd")
	@Column(name = "Date_de_naissance")
	private Date date_de_naissance;
	
	@Column(name = "numero_de_telephone")
	private String numero_de_telephone;

	@Column(name = "enabled")
	private boolean enabled=true;
	
	@Column(name = "Etat_civil")
	private String etat_civil;

	@Column(name = "Genre")
	private String genre;
	
	@Column(name = "Adresse")
	private String adresse;
	
	//Pour voir si le compte est autorisé ou pas
	@Column(name = "Etat_Compte")
	private String etatCompte ="Non Active" ;
	
	
	/*
	 * @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 * 
	 * @JoinTable(name = "AUTH_USER_AUTHORITY", joinColumns
	 * = @JoinColumn(referencedColumnName = "id"),inverseJoinColumns
	 * = @JoinColumn(referencedColumnName ="id")) private List<Authority>
	 * authorities;
	 */
	
	  @ManyToOne() 
	  private Authority authority;
	 
	@Column(name = "Last_login")
		private Date lastLogin;
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "CREATED_ON")
	private Date createdAt;

	@Column(name = "UPDATED_ON")
	private Date updatedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return Arrays.asList(new SimpleGrantedAuthority(authority.getRoleName()));
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	

	public Date getDate_de_naissance() {
		return date_de_naissance;
	}

	public void setDate_de_naissance(Date date_de_naissance) {
		this.date_de_naissance = date_de_naissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public void setPassword(String password) {
		this.password = password;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getNumero_de_telephone() {
		return numero_de_telephone;
	}

	public void setNumero_de_telephone(String numero_de_telephone) {
		this.numero_de_telephone = numero_de_telephone;
	}

	public String getEtat_civil() {
		return etat_civil;
	}

	public void setEtat_civil(String etat_civil) {
		this.etat_civil = etat_civil;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}	

}