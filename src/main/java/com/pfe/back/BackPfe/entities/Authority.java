package  com.pfe.back.BackPfe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Table(name = "AUTH_AUTHORITY")
@Entity
public class Authority implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	
    
	public Authority() {
		
	}

	public Authority(Long id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}



	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return roleName;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}







	
	
}
