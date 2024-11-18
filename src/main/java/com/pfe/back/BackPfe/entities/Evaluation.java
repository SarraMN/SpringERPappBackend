package com.pfe.back.BackPfe.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "evaluarion")
@Entity
public class Evaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private User employee;

	@ManyToOne
	@JoinColumn(name = "evaluator_id", nullable = false)
	private User evaluator; // L'évaluateur (RH)

	@Lob
	@Column(nullable = false)
	private String feedback;

	@Column(nullable = false)
	private double score; // Note de performance

	@Column(nullable = false)
	private LocalDate evaluationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public User getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(User evaluator) {
		this.evaluator = evaluator;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public LocalDate getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(LocalDate evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

}