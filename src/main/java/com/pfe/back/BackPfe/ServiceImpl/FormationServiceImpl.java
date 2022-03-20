package  com.pfe.back.BackPfe.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.pfe.back.BackPfe.entities.Formation;
import  com.pfe.back.BackPfe.repository.FormationRepo;
import  com.pfe.back.BackPfe.services.FormationService;

@Service
public class FormationServiceImpl implements FormationService{

	@Autowired
	private FormationRepo formationRepository;

	@Override
	public List<Formation> getAllFormations() {
		
		return formationRepository.findAll();
	}

	@Override
	public Formation updateFormation(Formation formation) {
		return formationRepository.save(formation);
	}

	@Override
	public void deleteFormation(Long id) {
		formationRepository.deleteById(id);
	}

	@Override
	public Formation addFormation(Formation formation) {
	
		return formationRepository.save(formation);

	}

	@Override
	public Formation findById(Long id) {
		Optional<Formation> formation = formationRepository.findById(id);
	     return  formation.isPresent() ? formation.get() : null;
	}


}
