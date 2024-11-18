package  com.pfe.back.BackPfe.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  com.pfe.back.BackPfe.EmailSender.SendEmailService;
import  com.pfe.back.BackPfe.EmailSender.EmailInfo;
import  com.pfe.back.BackPfe.entities.User;
import  com.pfe.back.BackPfe.services.AuthorityService;
import  com.pfe.back.BackPfe.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/Forgetpassword")
public class Forgetpassword {
	@Autowired
	private SendEmailService emailService;
	
	@Autowired
	private UserService userService;

	  @Autowired private AuthorityService authorityService;
	
	 @PostMapping("/sendmail") public User envoyermailfeverification(@RequestBody EmailInfo mail)
	 { 			
		User u= userService.findByEmail(mail.getDestinataire());

		 if(u!=null)
		 { ResponseEntity.ok("mail envoyé avec succée");
		        emailService.sendEmail(mail);

		        return u;
		 }
		 else
		 { 
		 return null;
		}
		
	 }
	 
	 @PutMapping("/updatePwd")
	 public User updatePwd(@RequestBody User U) {

		return userService.update_motdepasse(U.getId(), U.getPassword());
		
	 }

}
