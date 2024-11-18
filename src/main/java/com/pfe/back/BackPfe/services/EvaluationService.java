package com.pfe.back.BackPfe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.Evaluation;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.repository.EvaluationRepo;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepo evaluationRepository;

	// Create a new evaluation
	public Evaluation saveEvaluation(Evaluation evaluation) {
		return evaluationRepository.save(evaluation);
	}

	// Retrieve all evaluations
	public List<Evaluation> getAllEvaluations() {
		return evaluationRepository.findAll();
	}

	// Retrieve evaluations by employee (User)
	public List<Evaluation> getEvaluationsByEmployee(User employee) {
		return evaluationRepository.findByEmployee(employee);
	}

	// Retrieve a specific evaluation by ID
	public Optional<Evaluation> getEvaluationById(Long id) {
		return evaluationRepository.findById(id);
	}

	// Delete an evaluation by ID
	public void deleteEvaluation(Long id) {
		evaluationRepository.deleteById(id);
	}

	// Update an existing evaluation
	public Evaluation updateEvaluation(Long id, Evaluation updatedEvaluation) {
		return evaluationRepository.findById(id).map(existingEvaluation -> {
			existingEvaluation.setFeedback(updatedEvaluation.getFeedback());
			existingEvaluation.setScore(updatedEvaluation.getScore());
			existingEvaluation.setEvaluationDate(updatedEvaluation.getEvaluationDate());
			existingEvaluation.setEvaluator(updatedEvaluation.getEvaluator());
			existingEvaluation.setEmployee(updatedEvaluation.getEmployee());
			return evaluationRepository.save(existingEvaluation);
		}).orElse(null); // Return null if evaluation is not found
	}
}
