package com.biciplus.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	private String question;
	private String answer;
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Seller answerer;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Seller getAnswerer() {
		return answerer;
	}
	public void setAnswerer(Seller answerer) {
		this.answerer = answerer;
	}	
}
