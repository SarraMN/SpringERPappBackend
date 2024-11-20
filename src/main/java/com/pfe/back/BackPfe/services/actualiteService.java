package com.pfe.back.BackPfe.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.actualite;

@Service
public class actualiteService {
	@Autowired
	private com.pfe.back.BackPfe.repository.actualiteRepo actualiteRepo;
	
	
	public List<actualite> getAllActualitesForAdmin() {
		List<actualite> L= actualiteRepo.findAll();
		return L;
	}

	
	public List<actualite> getAllActualitesForCandidat() {
		List<actualite> L= actualiteRepo.findAll();
		List<actualite> L1= new ArrayList<actualite>();
		Calendar calendar = Calendar.getInstance();
		Date dateObj = calendar.getTime();

		for(int i=0;i<L.size();i++)
		{
			if(!(L.get(i).isArchivee())&&(dateObj.before(L.get(i).getDateExpiration())))
				L1.add(L.get(i));
			
		}
		return L1;
	}

	
	public actualite updateActualite(actualite actualite) {
		return actualiteRepo.save(actualite);
	}

	
	public void deleteActualite(Long id) {
       actualiteRepo.deleteById(id);		
	}

	
	public actualite ajouterActualite(actualite actualite) {
		Calendar calendar = Calendar.getInstance();
		  
		Date dateObj = calendar.getTime();
		actualite.setDatecreation(dateObj);
		return actualiteRepo.save(actualite);
	}

	
	public actualite findById(Long id) {
		Optional<actualite> actualite = actualiteRepo.findById(id);
	     return  actualite.isPresent() ? actualite.get() : null;
	}

	
	public void ChangerEtatActualite(Long id) {
		Optional<actualite> actualite = actualiteRepo.findById(id);
            if(actualite.isPresent())
            { 
            	actualite A=actualite.get();
            	if(A.isArchivee())
            		A.setArchivee(false);
            	else
            		A.setArchivee(true);
            	actualiteRepo.save(A);
            }
	}

}
