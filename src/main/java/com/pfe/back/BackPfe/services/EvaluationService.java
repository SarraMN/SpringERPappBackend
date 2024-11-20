package com.pfe.back.BackPfe.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.BackPfe.entities.Evaluation;
import com.pfe.back.BackPfe.repository.EvaluationRepo;
import com.pfe.back.BackPfe.repository.UserDetailsRepository;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepo evaluationRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	// Create a new evaluation
	public Evaluation saveEvaluation(Evaluation evaluation) {
		evaluation.setEvaluationDate(LocalDate.now());
		return evaluationRepository.save(evaluation);
	}

	// Retrieve all evaluations
	public List<Evaluation> getAllEvaluations() {
		return evaluationRepository.findAll();
	}

	public List<Evaluation> getEvaluationsByEmployee(Long employeeId) {
		// Fetch all evaluations
		List<Evaluation> allEvaluations = evaluationRepository.findAll();

		// Filter evaluations belonging to the given employee
		return allEvaluations.stream().filter(evaluation -> evaluation.getEmployee().getId() == employeeId)
				.collect(Collectors.toList());
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
