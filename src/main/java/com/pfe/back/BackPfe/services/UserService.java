package com.pfe.back.BackPfe.services;

import java.util.List;

import com.pfe.back.BackPfe.entities.User;



public interface UserService {
	
	public User findByUserName(String userName);
	
	public User add(User user);
	
	public User findById(Long id);

	public List<User> getUsers();
	
	public User findByEmail(String destinataire);

	public User update_motdepasse(long id, String password);
	
	public List<User> getUsersSaufAdmin();
	
	public List<User> getlisteEmployees();
	
	public List<User> getListRhs();

	public void deleteUser(Long id);

	public User updateUser(Long id, User user);

}
