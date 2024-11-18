package com.pfe.back.BackPfe.controllers;
  
import java.util.Calendar;
import java.util.Date;
import java.util.List;
  
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pfe.back.BackPfe.entities.Authority;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.responses.UserInfo;
import com.pfe.back.BackPfe.services.AuthorityService;
import
  com.pfe.back.BackPfe.services.UserService;
  
  @CrossOrigin(origins = "http://localhost:3000")
  @RestController  
  @RequestMapping("/api/users") 
  public class UserController {
  
  @Autowired private UserService userService;
  @Autowired private AuthorityService authorityService;

  
  public UserController() {
	  }
  
 
  @PostMapping("/addUser") public ResponseEntity<?> addUser(@RequestBody UserInfo
  userInfo) { 
  
  
  Authority authority= authorityService.findByRoleName((String) userInfo.getRoles());
  //System.out.println(A); 
  
  Calendar calendar = Calendar.getInstance();
  
  Date dateObj = calendar.getTime();
  
  User newUser = new User(); 
  newUser.setAdresse(userInfo.getAdressse()); 
  newUser.setNom(userInfo.getNom());
  newUser.setPrenom(userInfo.getPrenom()); 
  newUser.setPassword(userInfo.getPassword());
  newUser.setGenre(userInfo.getGenre()); 
  newUser.setDate_de_naissance(userInfo.getDate_de_naissance());
  newUser.setEmail(userInfo.getEmail()); 
  newUser.setEtat_civil(userInfo.getEtat_civil());
  newUser.setUserName(userInfo.getUserName()); 
  newUser.setAuthority(authority);
  newUser.setNumero_de_telephone(userInfo.getNumero_de_telephone());
  newUser.setCreatedAt(dateObj); 
  
   if (userService.findByEmail(newUser.getEmail())!=null || userService.findByUserName(newUser.getUserName())!=null)
  {
	  return new ResponseEntity<>("Email et User sont déja utilisés ",HttpStatus.BAD_REQUEST);
  }
   else if(userService.findByUserName(newUser.getUserName())!=null ) {
	  return new ResponseEntity<>("Username déja utilisé",HttpStatus.BAD_REQUEST);
  }
  else if(userService.findByEmail(newUser.getEmail())!=null)
  {
	  return new ResponseEntity<>("Email déja utilisé",HttpStatus.BAD_REQUEST);
  }
  

  else {
		  userService.add(newUser); 
		  return ResponseEntity.ok("user ajouté avec succée");
	  }
  }

  @GetMapping("/allUsersExceptAdmin")
  public List<User> getUsersSaufAdmin() { 
	  return userService.getUsersSaufAdmin();
  
  }
  
  @GetMapping("/employees")
  public List<User> getlisteEmployees() { 
	  return userService.getlisteEmployees();
  
  }
  @GetMapping("/listRhs")
  public List<User> getlisteRhs() { 
	  return userService.getListRhs();
  
  }
  @GetMapping("/allUsers")
  public List<User> getUsers() {
	  return userService.getUsers();
  }
  
  @GetMapping("/{id}")
 	public User getUserById(@PathVariable Long id) {
 		return userService.findById(id);
 	}
  
  @DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
	  userService.deleteUser(id);
	}
  
  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {
      return userService.updateUser(id, user);
  }

  
  }
 
