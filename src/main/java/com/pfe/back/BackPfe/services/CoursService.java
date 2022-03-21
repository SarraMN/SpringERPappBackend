package com.pfe.back.BackPfe.services;

import java.util.List;

import com.pfe.back.BackPfe.entities.Cours;


public interface CoursService {

public List<Cours> getAllCours();
	
	public Cours updateCours(Cours cours);
	
	public void deleteCours(Long id);
	
	public Cours addCours(Cours cours);

	public Cours findById(Long id);
	
	public List<Cours> getCoursByIdFromation(Long idFormation);
}
