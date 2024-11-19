package com.pfe.back.BackPfe.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.repository.UserDetailsRepository;
import com.pfe.back.BackPfe.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	public User findByEmail(String destinataire) {
		return userRepo.findByEmail(destinataire);

	}

	@Override
	public User add(User user) {
		String pwd = user.getPassword();
		user.setPassword(passwordEncoder.encode(pwd));
		return userRepo.save(user);
	}

	@Override
	public List<User> getUsers() {

		return userRepo.findAll();
	}

	@Override
	public User updateUser(Long id, User user) {
		Optional<User> user1 = userRepo.findById(id);
		if (user1.isPresent()) {
			User user2 = user1.get();
			user2.setAdresse(user.getAdresse());
			user2.setAuthority(user.getAuthority());
			user2.setNom(user.getNom());
			user2.setPassword(passwordEncoder.encode(user.getPassword()));
			user2.setPrenom(user.getPrenom());
			user2.setEmail(user.getEmail());
			user2.setGenre(user.getGenre());
			user2.setEtat_civil(user.getEtat_civil());
			user2.setUserName(user.getUsername());
			user2.setNumero_de_telephone(user.getNumero_de_telephone());
			user2.setSoldeLeaves(user.getSoldeLeaves());
			user2.setImage(user.getImage());
			return userRepo.save(user2);
		}
		return null;

	}

	@Override
	public User update_motdepasse(long id, String password) {
		Optional<User> user1 = userRepo.findById(id);
		if (user1.isPresent()) {
			User user2 = user1.get();
			user2.setPassword(passwordEncoder.encode(password));
			return userRepo.save(user2);
		}
		return null;
	}

	@Override
	public List<User> getUsersSaufAdmin() {
		List<User> Users = userRepo.findAll();

		List<User> UsersSansAdmin = new ArrayList<User>();
		for (int i = 0; i < Users.size(); i++) {

			if (Users.get(i).getAuthority().getRoleName().equals("User_RH")
					|| Users.get(i).getAuthority().getRoleName().equals("User_Candidat")) {
				UsersSansAdmin.add(Users.get(i));
			}
		}
		return UsersSansAdmin;
	}

	@Override
	public List<User> getlisteEmployees() {

		List<User> Users = userRepo.findAll();

		List<User> employees = new ArrayList<User>();
		for (int i = 0; i < Users.size(); i++) {

			if (Users.get(i).getAuthority().getRoleName().equals("User_Employee")) {
				employees.add(Users.get(i));
			}
		}
		return employees;
	}

	@Override
	public List<User> getListRhs() {
		List<User> Users = userRepo.findAll();

		List<User> candidats = new ArrayList<User>();
		for (int i = 0; i < Users.size(); i++) {

			if (Users.get(i).getAuthority().getRoleName().equals("User_RH")) {
				candidats.add(Users.get(i));
			}
		}
		return candidats;
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User findById(Long id) {
		Optional<User> user = userRepo.findById(id);
		
		return user.isPresent() ? user.get() : null;
	}
	
	@Override
	public boolean verifPassword(Long id, String password) {
		Optional<User> user1=userRepo.findById(id);
		User user2=user1.get();
		if(passwordEncoder.matches(password,user2.getPassword()))
		{return true;}
		else return false;
	}
}
