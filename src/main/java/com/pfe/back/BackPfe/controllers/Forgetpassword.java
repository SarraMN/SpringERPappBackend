package  com.pfe.back.BackPfe.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  com.pfe.back.BackPfe.EmailSender.SendEmailService;
import  com.pfe.back.BackPfe.EmailSender.emailinfo;
import  com.pfe.back.BackPfe.entities.User;
import  com.pfe.back.BackPfe.services.AuthorityService;
import  com.pfe.back.BackPfe.services.UserService;

//@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/Forgetpassword")
public class Forgetpassword {
	@Autowired
	private SendEmailService SES;
	
	@Autowired
	private UserService userService;

	  @Autowired private AuthorityService authorityService;
	
	 @PostMapping("/sendmail") public User envoyermailfeverification(@RequestBody emailinfo mail)
	 { 			
		User u= userService.findByEmail(mail.getDestinataire());

		 if(u!=null)
		 { ResponseEntity.ok("mail envoyé avec succée");
		        SES.sendEmail(mail);

		        return u;
		 }
		 else
		 { 
		 return null;
		}
		
	 }
	 
		/*
		 * @PutMapping("/update") public User update(@RequestBody UserInfo U) {
		 * 
		 * Authority A= authorityService.findByRoleName((String) U.getRoles());
		 * 
		 * SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd"); Calendar calendar
		 * = Calendar.getInstance();
		 * 
		 * Date dateObj = calendar.getTime();
		 * 
		 * User U1 =new User(); U1.setAdresse(U.getAdressse()); U1.setNom(U.getNom());
		 * U1.setPrenom(U.getPrenom()); U1.setPassword(U.getPassword());
		 * U1.setGenre(U.getGenre()); U1.setDate_de_naissance(U.getDate_de_naissance());
		 * U1.setEmail(U.getEmail()); U1.setEtat_civil(U.getEtat_civil());
		 * U1.setUserName(U.getUserName()); U1.setAuthority(A);
		 * U1.setNumero_de_telephone(U.getNumero_de_telephone());
		 * U1.setUpdatedAt(dateObj); // U1.setCreatedAt(U.getCreatedAt());
		 * 
		 * return userService.update(U.getId(), U1);
		 * 
		 * 
		 * 
		 * }
		 */
	 
	 @PutMapping("/update")
	 public User update_motdepasseoublie(@RequestBody User U) {

		return userService.update_motdepasse(U.getId(), U.getPassword());
		
	 	

	 }

}
