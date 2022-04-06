package  com.pfe.back.BackPfe.ServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		Calendar calendar = Calendar.getInstance();
		  
		Date dateObj = calendar.getTime();
		cours.setDateMdf(dateObj);
		return coursRepository.save(cours);
	}

	@Override
	public void deleteCours(Long id) {
		coursRepository.deleteById(id);
	}

	@Override
	public Cours addCours(Cours cours) {
	

		Calendar calendar = Calendar.getInstance();
		  
		Date dateObj = calendar.getTime();
		cours.setDateCreation(dateObj);
		cours.setDateMdf(dateObj);
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

	@Override
	public void ArchiverCours(Long id) {
		Optional<Cours> cours = coursRepository.findById(id);
		if(cours.isPresent()) {
			Cours cours1=cours.get();
			if(cours1.getEtat().equals("Non archivé")) {
				cours1.setEtat("Archivé");
				
			}
			else cours1.setEtat("Non archivé");
			coursRepository.save(cours1);
		}
		 
	}



}
