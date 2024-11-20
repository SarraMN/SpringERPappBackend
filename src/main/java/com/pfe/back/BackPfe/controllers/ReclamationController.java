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

import com.pfe.back.BackPfe.entities.Reclamation;
import com.pfe.back.BackPfe.services.ReclamationService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/Reclamations")
public class ReclamationController {

	public ReclamationController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private ReclamationService reclamationService;
	
	
	@GetMapping("")
    public List<Reclamation> getAllReclamation(){
	   return reclamationService.getAllReclamations();
}
    @PutMapping("/{id}")
	public Reclamation update(@PathVariable Long id ,@RequestBody Reclamation reclamation) {
    	return reclamationService.updateReclamation(reclamation);
	}
    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
    	reclamationService.deleteReclamation(id);
    }
    @PostMapping
	public Reclamation add(@RequestBody Reclamation reclamation) {
    	return reclamationService.addReclamation(reclamation);
    }
    @GetMapping("/{id}")
	public Reclamation getReclamationById(@PathVariable Long id) {
		return reclamationService.findById(id);
	}
    
    @GetMapping("/candidat/{idCandidat}")
    public List<Reclamation> getReclamationByIdCandidat(@PathVariable Long idCandidat){
    	return reclamationService.getReclamationByIdCandidat(idCandidat);
	}
    @PutMapping("/ChangerEtatReclamation/{id}")
   	public void archiverReclamation(@PathVariable Long id) {
       	 reclamationService.ChangerEtatReclamation(id);
   	}
    
    @PutMapping("/repondreReclamation/{id}")
   	public void repondreReclamation(@PathVariable Long id) {
       	 reclamationService.repondreReclamation(id);
   	}
    
    @GetMapping("/traitees")
    public List<Reclamation> reclamationsTraitee() {
    	return reclamationService.reclamationsTraitee();
    }
    
    @GetMapping("/NonTraitees")
    public List<Reclamation> reclamationsNonTraitee() {
    	return reclamationService.reclamationsNonTraitee();
    }

}