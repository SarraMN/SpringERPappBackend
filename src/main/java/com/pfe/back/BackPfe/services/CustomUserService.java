package  com.pfe.back.BackPfe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import  com.pfe.back.BackPfe.entities.User;
import  com.pfe.back.BackPfe.repository.UserDetailsRepository;


@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("CustomUserService");

		User user=userDetailsRepository.findByUserName(username);
	//	System.out.println(user.getUsername());

		if(null==user) {
			throw new UsernameNotFoundException("User Not Found with userName "+ username);

		}
		System.out.println(user.toString());

		return user;
		
	}

}
