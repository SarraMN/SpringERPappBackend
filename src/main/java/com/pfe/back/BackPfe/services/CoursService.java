package com.pfe.back.BackPfe.services;

import java.util.List;

import com.pfe.back.BackPfe.entities.Cours;


public interface CoursService {

public List<Cours> getAllCourss();
	
	public Cours updateCours(Cours cours);
	
	public void deleteCours(Long id);
	
	public Cours addCours(Cours cours);

	public Cours findById(Long id);
}
