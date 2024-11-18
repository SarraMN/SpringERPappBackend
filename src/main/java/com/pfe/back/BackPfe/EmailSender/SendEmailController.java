package  com.pfe.back.BackPfe.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/mail")
public class SendEmailController {
	@Autowired
	private SendEmailService SES;
	
	
	 @PostMapping("/Envoie")
    public ResponseEntity<?> EnvoyerMail(@RequestBody EmailInfo mail1){
		System.out.println("coucou sou");

        SES.sendEmail(mail1);
        return new ResponseEntity(HttpStatus.OK);
    }

}
