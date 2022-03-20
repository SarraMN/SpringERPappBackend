package  com.pfe.back.BackPfe.controllers;

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

import  com.pfe.back.BackPfe.entities.Formation;
import  com.pfe.back.BackPfe.services.FormationService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/formation")
public class FormationController {

	public FormationController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private FormationService formationService;
	
	
	@GetMapping("")
    public List<Formation> getAllFormations(){
	   return formationService.getAllFormations();
}
    @PutMapping("/{id}")
	public Formation update(@PathVariable Integer id ,@RequestBody Formation formation) {
    	return formationService.updateFormation(formation);
	}
    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
    	formationService.deleteFormation(id);
    }
    @PostMapping
	public Formation add(@RequestBody Formation formation) {
    	return formationService.addFormation(formation);
    }
    @GetMapping("/{id}")
	public Formation getFormationById(@PathVariable Long id) {
		return formationService.findById(id);
	}
}
