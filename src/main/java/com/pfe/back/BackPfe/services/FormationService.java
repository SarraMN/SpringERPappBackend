package com.pfe.back.BackPfe.services;

import java.util.List;

import com.pfe.back.BackPfe.entities.Formation;


public interface FormationService {

public List<Formation> getAllFormations();
	
	public Formation updateFormation(Formation formation);
	
	public void deleteFormation(Long id);
	
	public Formation addFormation(Formation formation);

	public Formation findById(Long id);
}
