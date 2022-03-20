package  com.pfe.back.BackPfe.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.pfe.back.BackPfe.entities.Authority;
import  com.pfe.back.BackPfe.entities.User;
import  com.pfe.back.BackPfe.repository.AuthorityRepo;
import  com.pfe.back.BackPfe.services.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepo authorityRepo;

	public Authority findByRoleName(String roleName) {
		return authorityRepo.findByRoleName(roleName);
	}

	@Override
	public Authority add(Authority authority) {
		
		return authorityRepo.save(authority);
	}
	
	
}
