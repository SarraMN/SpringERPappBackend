package  com.pfe.back.BackPfe.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/*import tn.com.abt.ms.emailSender.EmailRepository;
import tn.com.abt.ms.emailSender.emailinfo;*/

@Service
public class SendEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired 
	private EmailRepository ER;
	
	public void sendEmail(emailinfo maill)
	{
		System.out.println("sending mail");
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("amdounisirrine90@gmail.com");
		simpleMailMessage.setTo(maill.getDestinataire());
		simpleMailMessage.setSubject(maill.getTopic());
		simpleMailMessage.setText(maill.getBody());
		javaMailSender.send(simpleMailMessage);
		System.out.println("sent mail...");	
	}
	
	   public ResponseEntity ajouterMail(@RequestBody emailinfo mail1){
	        emailinfo mail2 = ER.save(mail1);
	        return new ResponseEntity<>(mail2, HttpStatus.CREATED);
	    }



}
