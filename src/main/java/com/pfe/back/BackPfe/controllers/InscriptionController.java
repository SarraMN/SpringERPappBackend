package  com.pfe.back.BackPfe.controllers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.BackPfe.entities.Authority;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.responses.MessageResponse;
import com.pfe.back.BackPfe.responses.UserInfo;
import com.pfe.back.BackPfe.services.AuthorityService;
import com.pfe.back.BackPfe.services.UserService;
//@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api")
public class InscriptionController {
	
	@Autowired
	private UserService userService;
	
	
	  @Autowired private AuthorityService authorityService;
	  
	 /*
	 * @PostMapping("/signup") public ResponseEntity<?> SignUp(@RequestBody User
	 * user) { Authority A= authorityService.findByRoleName(user.getRoles());
	 * user.setAuthority(A); userService.add(user);
	 * System.out.println(user.getPassword());
	 * System.out.println(user.getAuthorities()); return
	 * ResponseEntity.ok("user ajouté avec succée");
	 * 
	 * }
	 */
	 
	 
	
	  @PostMapping("/signup") public ResponseEntity<?> SignUp(@RequestBody UserInfo
	  U) { System.out.println(U.getRoles());
	  
	  
	  Authority A= authorityService.findByRoleName((String) U.getRoles());
	  //System.out.println(A); 
	  
	  SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
	  Calendar calendar = Calendar.getInstance();
	  
	  Date dateObj = calendar.getTime();
	  
	  User U1 =new User(); 
	  U1.setAdresse(U.getAdressse()); 
	  U1.setNom(U.getNom());
	  U1.setPrenom(U.getPrenom()); 
	  U1.setPassword(U.getPassword());
	  U1.setGenre(U.getGenre()); 
	  U1.setDate_de_naissance(U.getDate_de_naissance());
	  U1.setEmail(U.getEmail()); 
	  U1.setEtat_civil(U.getEtat_civil());
	  U1.setUserName(U.getUserName()); 
	  U1.setAuthority(A);
	  U1.setNumero_de_telephone(U.getNumero_de_telephone());
	  U1.setCreatedAt(dateObj); 
	  
	   if (userService.findByEmail(U1.getEmail())!=null || userService.findByUserName(U1.getUserName())!=null)
	  {
		  return new ResponseEntity<>("Email et User sont déja utilisés ",HttpStatus.BAD_REQUEST);
	  }
	   else if(userService.findByUserName(U1.getUserName())!=null ) {
		  return new ResponseEntity<>("Username déja utilisé",HttpStatus.BAD_REQUEST);
	  }
	  else if(userService.findByEmail(U1.getEmail())!=null)
	  {
		  return new ResponseEntity<>("Email déja utilisé",HttpStatus.BAD_REQUEST);
	  }
	  
	
	  else {
	  userService.add(U1); 
	  return ResponseEntity.ok("user ajouté avec succée");
	  }
	  }

}
