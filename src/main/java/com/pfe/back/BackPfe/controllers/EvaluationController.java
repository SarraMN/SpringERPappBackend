package com.pfe.back.BackPfe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.BackPfe.entities.Evaluation;
import com.pfe.back.BackPfe.entities.User;
import com.pfe.back.BackPfe.services.EvaluationService;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;

	// Create a new evaluation
	@PostMapping
	public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
		Evaluation savedEvaluation = evaluationService.saveEvaluation(evaluation);
		return new ResponseEntity<>(savedEvaluation, HttpStatus.CREATED);
	}

	// Retrieve all evaluations
	@GetMapping
	public ResponseEntity<List<Evaluation>> getAllEvaluations() {
		List<Evaluation> evaluations = evaluationService.getAllEvaluations();
		return new ResponseEntity<>(evaluations, HttpStatus.OK);
	}

	// Retrieve evaluations by employee (User)
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<Evaluation>> getEvaluationsByEmployee(@RequestBody User employee) {
		List<Evaluation> evaluations = evaluationService.getEvaluationsByEmployee(employee);
		return new ResponseEntity<>(evaluations, HttpStatus.OK);
	}

	// Retrieve a specific evaluation by ID
	@GetMapping("/{id}")
	public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
		Optional<Evaluation> evaluation = evaluationService.getEvaluationById(id);
		return evaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Delete an evaluation by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
		evaluationService.deleteEvaluation(id);
		return ResponseEntity.noContent().build();
	}

	// Update an existing evaluation
	@PutMapping("/{id}")
	public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id,
			@RequestBody Evaluation updatedEvaluation) {
		Evaluation updated = evaluationService.updateEvaluation(id, updatedEvaluation);
		if (updated != null) {
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}