package com.pfe.back.BackPfe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.BackPfe.entities.actualite;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/actualite")
public class actualiteController {
	@Autowired private com.pfe.back.BackPfe.services.actualiteService actualiteService;

	@GetMapping("/getAllActualitesForAdmin")
    public List<actualite> getAllActualitesForAdmin(){
	   return actualiteService.getAllActualitesForAdmin();
}	
    @GetMapping("/getAllActualitesForCandidat")
public List<actualite> getAllActualitesForCandidat(){
   return actualiteService.getAllActualitesForCandidat();
}
@PutMapping("/updateActualite")
	public actualite update(@RequestBody actualite actualite) {
  	return actualiteService.updateActualite(actualite);
	}
   @DeleteMapping("/deleteActualite/{id}")
    public void delete(@PathVariable Long id) {
	   actualiteService.deleteActualite(id);
}
   @PostMapping("/ajouterActualite")
  	public actualite add(@RequestBody actualite actualite) {
      	return actualiteService.ajouterActualite(actualite);
      }
   @GetMapping("/findById/{id}")
  	public actualite findById(@PathVariable Long id) {
  		return actualiteService.findById(id);
  	} 
   @PutMapping("/ChangerEtatActualite/{id}")
  	public void ChangerEtatActualite(@PathVariable Long id) {
	   actualiteService.ChangerEtatActualite(id);
  	}
}

