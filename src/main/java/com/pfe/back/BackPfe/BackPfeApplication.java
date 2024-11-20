package com.pfe.back.BackPfe;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pfe.back.BackPfe.EmailSender.EmailInfo;
import com.pfe.back.BackPfe.EmailSender.SendEmailService;
import com.pfe.back.BackPfe.entities.Authority;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.repository.UserDetailsRepository;
import com.pfe.back.BackPfe.services.AuthorityService;

@SpringBootApplication
public class BackPfeApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private SendEmailService SES;

	public static void main(String[] args) {
		SpringApplication.run(BackPfeApplication.class, args);
	}

	@PostConstruct
	protected void init() {

		Authority adminRole = new Authority(null, "Admin");
		Authority rhRole = new Authority(null, "User_RH");
		Authority employeeRole = new Authority(null, "User_Employee");

		authorityService.add(adminRole);
		authorityService.add(rhRole);
		authorityService.add(employeeRole);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		User user = new User();

		user.setUserName("sirine");
		user.setNom("sirine");
		user.setPrenom("amdouni");
		user.setPassword(passwordEncoder.encode("sirine123"));
		user.setAdresse("mornaguia");
		user.setEmail("amdounisirine80@gmail.com");
		user.setNumero_de_telephone("+216 22929388");
		try {
			user.setDate_de_naissance(formatter.parse("2000-02-22"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setGenre("Femme");
		user.setEtat_civil("Celibataire");
		user.setAuthority(adminRole);

		User user2 = new User();

		user2.setUserName("sarra");
		user2.setNom("mannai");
		user2.setPrenom("sarra");
		user2.setPassword(passwordEncoder.encode("sarra123"));
		user2.setAdresse("mornaguia");
		user2.setEmail("sarahmannai2000@gmail.com");
		user2.setNumero_de_telephone("+216 22929388");
		try {
			user2.setDate_de_naissance(formatter.parse("2000-04-02"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user2.setGenre("Femme");
		user2.setEtat_civil("Celibataire");
		user2.setAuthority(adminRole);
		if (userDetailsRepository.findByUserName(user2.getUsername()) == null
				&& userDetailsRepository.findByUserName(user2.getUsername()) == null) {
			userDetailsRepository.save(user);
			userDetailsRepository.save(user2);
		}

		User user3 = new User();
		user3.setUserName("employee1");
		user3.setNom("John");
		user3.setPrenom("Doe");
		user3.setPassword(passwordEncoder.encode("employee123"));
		user3.setAdresse("Tunis");
		user3.setEmail("employee1@example.com");
		user3.setNumero_de_telephone("+216 55555555");
		try {
			user3.setDate_de_naissance(formatter.parse("1995-06-15"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user3.setGenre("Homme");
		user3.setEtat_civil("Celibataire");
		user3.setAuthority(employeeRole);

		if (userDetailsRepository.findByUserName(user.getUsername()) == null) {
			userDetailsRepository.save(user);
		}
		if (userDetailsRepository.findByUserName(user2.getUsername()) == null) {
			userDetailsRepository.save(user2);
		}
		if (userDetailsRepository.findByUserName(user3.getUsername()) == null) {
			userDetailsRepository.save(user3);
		}
		
		// Création d'un utilisateur RH
	    User rhUser = new User();
	    rhUser.setUserName("rh_user");
	    rhUser.setNom("Ben");
	    rhUser.setPrenom("Ali");
	    rhUser.setPassword(passwordEncoder.encode("rh123"));
	    rhUser.setAdresse("Tunis");
	    rhUser.setEmail("rh_user@example.com");
	    rhUser.setNumero_de_telephone("+216 12345678");
	    try {
	        rhUser.setDate_de_naissance(formatter.parse("1985-09-10"));
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    rhUser.setGenre("Homme");
	    rhUser.setEtat_civil("Marié");
	    rhUser.setAuthority(rhRole);

	    // Sauvegarder l'utilisateur RH s'il n'existe pas déjà
	    if (userDetailsRepository.findByUserName(rhUser.getUsername()) == null) {
	        userDetailsRepository.save(rhUser);
	    }
		EmailInfo M = new EmailInfo();
		M.setBody("hello");
		M.setDestinataire("amdounisirine80@gmail.com");
		SES.ajouterMail(M);

	}

}
