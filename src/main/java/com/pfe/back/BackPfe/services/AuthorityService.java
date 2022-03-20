package  com.pfe.back.BackPfe.services;

import  com.pfe.back.BackPfe.entities.Authority;
import  com.pfe.back.BackPfe.entities.User;


public interface AuthorityService {
	 
	public Authority add(Authority authority);
	public Authority findByRoleName(String roleName);

}
