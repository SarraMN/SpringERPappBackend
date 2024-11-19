package  com.pfe.back.BackPfe.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.pfe.back.BackPfe.config.JWTTokenHelper;
import  com.pfe.back.BackPfe.entities.User;
import  com.pfe.back.BackPfe.requests.AuthenticationRequest;
import  com.pfe.back.BackPfe.responses.LoginResponse;
import  com.pfe.back.BackPfe.responses.UserInfo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
		
		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword()));
         
		
		User user=(User)authentication.getPrincipal();
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());

		LoginResponse response=new LoginResponse();
		response.setToken(jwtToken);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user){
		User userObj=(User) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo= new UserInfo();
	    userInfo.setNom(userObj.getNom());
	    userInfo.setId(userObj.getId());
		userInfo.setPrenom(userObj.getPrenom());
		userInfo.setDate_de_naissance(userObj.getDate_de_naissance());
		userInfo.setNumero_de_telephone(userObj.getNumero_de_telephone());
		userInfo.setEmail(userObj.getEmail());
		userInfo.setGenre(userObj.getGenre());
		userInfo.setAdressse(userObj.getAdresse());
        userInfo.setEtat_civil(userObj.getEtat_civil());
		userInfo.setRoles(userObj.getAuthorities().toArray());
		userInfo.setUserName(userObj.getUsername());
		userInfo.setPassword(userObj.getPassword());
		if(userObj.getImage()==null)
		{
			userInfo.setIdimage((long) 0);
		}
		else
		userInfo.setIdimage(userObj.getImage().getId());
		userInfo.setSoldeLeaves(userObj.getSoldeLeaves());
		return ResponseEntity.ok(userInfo);
		
		
		
	}
}
