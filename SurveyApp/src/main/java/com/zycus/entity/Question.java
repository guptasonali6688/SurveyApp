package com.zycus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "QUESTION_TB")
public class Question {

	@Id
	@GeneratedValue
	private int id;

	private String questionText;

	@ManyToOne
	@JoinColumn(name = "survey_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Survey survey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@JsonIgnore
	public Survey getSurvey() {
		return survey;
	}

	@JsonProperty
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", survey=" + survey + "]";
	}

}
