
  package com.pfe.back.BackPfe.controllers;
  
  import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import
  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RestController;

import com.pfe.back.BackPfe.entities.Formation;
import com.pfe.back.BackPfe.entities.User; import
  com.pfe.back.BackPfe.services.UserService;
  
  @CrossOrigin(origins = "http://localhost:3000")
  @RestController  
  @RequestMapping("/api/users") 
  public class UserController {
  
  @Autowired private UserService userService;
  
  public UserController() { // TODO Auto-generated constructor stub 
	  }
  
 
  @GetMapping("/profsEtCandidats")
  public List<User> getUsersSaufAdmin() { 
	  return userService.getUsersSaufAdmin();
  
  }
  
  @GetMapping("/formateurs")
  public List<User> getlisteFormateurs() { 
	  return userService.getlisteFormateurs();
  
  }
  @GetMapping("/candidats")
  public List<User> getlisteCandidats() { 
	  return userService.getlisteCandidats();
  
  }
  @GetMapping("")
  public List<User> getUsers() {
	  return userService.getUsers();
  }
  
  @GetMapping("/{id}")
 	public User getUserById(@PathVariable Long id) {
 		return userService.findById(id);
 	}
  
  }
 
