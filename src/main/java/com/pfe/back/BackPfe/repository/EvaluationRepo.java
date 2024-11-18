package com.pfe.back.BackPfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.BackPfe.entities.Evaluation;
import com.pfe.back.BackPfe.entities.User;

@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {

	// Find evaluations by the employee (User object)
	List<Evaluation> findByEmployee(User employee);

}
