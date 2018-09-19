package com.zycus.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_QUESTIONS")
public class Question {
	
	@Id
	@GeneratedValue
	private int Qid;
	private String ques;
	
	@OneToMany
	private Set<response> responses;
	
	@ManyToOne
	@JoinColumn(name="surveyID")
	private Survey survey;

	public int getQid() {
		return Qid;
	}

	public void setQid(int qid) {
		Qid = qid;
	}

	public String getQues() {
		return ques;
	}

	public void setQues(String ques) {
		this.ques = ques;
	}

	public Set<response> getResponses() {
		return responses;
	}

	public void setResponses(Set<response> responses) {
		this.responses = responses;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "Question [Qid=" + Qid + ", ques=" + ques + ", responses=" + responses + ", survey=" + survey + "]";
	}

	

}
