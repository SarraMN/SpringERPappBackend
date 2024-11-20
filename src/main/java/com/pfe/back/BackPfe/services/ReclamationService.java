package com.pfe.back.BackPfe.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.Reclamation;
import com.pfe.back.BackPfe.repository.ReclamationRepo;

@Service
public class ReclamationService {
	@Autowired
	private ReclamationRepo reclamationRepository;
	
	public List<Reclamation> getAllReclamations() {
		
		return reclamationRepository.findAll();
	}

	public Reclamation updateReclamation(Reclamation reclamation) {
		Calendar calendar = Calendar.getInstance();		  
		Date dateObj = calendar.getTime();
		reclamation.setDatetraitement(dateObj);
		return reclamationRepository.save(reclamation);
	}

	public void deleteReclamation(Long id) {
		reclamationRepository.deleteById(id);
	}

	public Reclamation addReclamation(Reclamation reclamation) {
		Calendar calendar = Calendar.getInstance();		  
		Date dateObj = calendar.getTime();
		reclamation.setDateenvoie(dateObj);
		reclamation.setTraitee(false);
		return reclamationRepository.save(reclamation);

	}

	public Reclamation findById(Long id) {
		Optional<Reclamation> reclamation = reclamationRepository.findById(id);
	     return  reclamation.isPresent() ? reclamation.get() : null;
	}

	public List<Reclamation> getReclamationByIdCandidat(Long idCandidat) {
		List<Reclamation> listeReclamation = reclamationRepository.findAll();
		List<Reclamation> listeReclamationCandidat = new ArrayList<Reclamation>();
         for(int i=0;i<listeReclamation.size();i++) {
			
			if(listeReclamation.get(i).getCandidat().getId()==idCandidat) 
			{
				listeReclamationCandidat.add(listeReclamation.get(i));
			}
         }
		return listeReclamationCandidat;
	}

	public void ChangerEtatReclamation(Long id) {
		Optional<Reclamation> reclamation = reclamationRepository.findById(id);
		if(reclamation.isPresent()) {
			Reclamation reclamation1=reclamation.get();
			if(reclamation1.getTraitee()== false) {
				reclamation1.setTraitee(true);				
			}
			reclamationRepository.save(reclamation1);
		}		 
	}
	
	public void repondreReclamation(Long id) {
		Optional<Reclamation> reclamation = reclamationRepository.findById(id);
		if(reclamation.isPresent()) {
			Reclamation reclamation1=reclamation.get();
			if(reclamation1.getTraitee()== false) {
				reclamation1.setTraitee(true);				
			}
			reclamationRepository.save(reclamation1);
		}
		 
	}

	public List<Reclamation> reclamationsTraitee() {
		List<Reclamation> listeReclamation = reclamationRepository.findAll();
		List<Reclamation> listeReclamationTraitee = new ArrayList<Reclamation>();
         for(int i=0;i<listeReclamation.size();i++) {
			
			if(listeReclamation.get(i).getTraitee()==true) 
			{
				listeReclamationTraitee.add(listeReclamation.get(i));
			}
         }
		return listeReclamationTraitee;
	}

	public List<Reclamation> reclamationsNonTraitee() {
		List<Reclamation> listeReclamation = reclamationRepository.findAll();
		List<Reclamation> listeReclamationNonTraitee = new ArrayList<Reclamation>();
         for(int i=0;i<listeReclamation.size();i++) {
			
			if(listeReclamation.get(i).getTraitee()==false) 
			{
				listeReclamationNonTraitee.add(listeReclamation.get(i));
			}
         }
		return listeReclamationNonTraitee;
	}

}
