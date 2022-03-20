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

import  com.pfe.back.BackPfe.entities.Cours;
import  com.pfe.back.BackPfe.services.CoursService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/cours")
public class CoursController {

	public CoursController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private CoursService coursService;
	
	
	@GetMapping("")
    public List<Cours> getAllCourss(){
	   return coursService.getAllCourss();
}
    @PutMapping("/{id}")
	public Cours update(@PathVariable Integer id ,@RequestBody Cours cours) {
    	return coursService.updateCours(cours);
	}
    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
    	coursService.deleteCours(id);
    }
    @PostMapping
	public Cours add(@RequestBody Cours cours) {
    	return coursService.addCours(cours);
    }
    @GetMapping("/{id}")
	public Cours getCoursById(@PathVariable Long id) {
		return coursService.findById(id);
	}
}
