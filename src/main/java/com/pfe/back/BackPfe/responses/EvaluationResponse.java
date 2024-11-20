package com.pfe.back.BackPfe.responses;

import java.time.LocalDate;

import com.pfe.back.BackPfe.entities.Evaluation;

public class EvaluationResponse {
	long id;
	String employee;
	String evaluator;
	double score;
	LocalDate date;
	 String feedback;

	public EvaluationResponse() {
		// TODO Auto-generated constructor stub
	}

	public EvaluationResponse(Evaluation eval) {
		this.id = eval.getId();
		this.employee = eval.getEmployee().getNom()+' '+eval.getEmployee().getPrenom();
		this.evaluator =  eval.getEvaluator().getNom()+' '+eval.getEvaluator().getPrenom();
		this.score = eval.getScore();
		this.date = eval.getEvaluationDate();
		this.feedback=eval.getFeedback();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEvaluator() {
		return evaluator;
	}
	public void setfeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public String getfeedback() {
		return feedback;
	}

	public void setEvaluator(String evaluator) {
		this.evaluator = evaluator;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
