package  com.pfe.back.BackPfe.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import  com.pfe.back.BackPfe.entities.Cours;
import com.pfe.back.BackPfe.entities.User;
import  com.pfe.back.BackPfe.repository.CoursRepo;
import  com.pfe.back.BackPfe.services.CoursService;

@Service
public class CoursServicesImpl implements CoursService{

	@Autowired
	private CoursRepo coursRepository;

	@Override
	public List<Cours> getAllCours() {
		
		return coursRepository.findAll();
	}

	@Override
	public Cours updateCours(Cours cours) {
		return coursRepository.save(cours);
	}

	@Override
	public void deleteCours(Long id) {
		coursRepository.deleteById(id);
	}

	@Override
	public Cours addCours(Cours cours) {
	
		return coursRepository.save(cours);

	}

	@Override
	public Cours findById(Long id) {
		Optional<Cours> cours = coursRepository.findById(id);
	     return  cours.isPresent() ? cours.get() : null;
	}

	@Override
	public List<Cours> getCoursByIdFromation(Long idFormation) {
		List<Cours> listeCours = coursRepository.findAll();
		List<Cours> listeCoursfomation = new ArrayList<Cours>();
         for(int i=0;i<listeCours.size();i++) {
			
			if(listeCours.get(i).getFormation().getId()==idFormation) 
			{
				listeCoursfomation.add(listeCours.get(i));
			}
         }
		return listeCoursfomation;
	}


}
