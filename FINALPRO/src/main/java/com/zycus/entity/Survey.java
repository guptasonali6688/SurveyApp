package com.zycus.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_SURVEY")
public class Survey {
	
	
	@Id
	@GeneratedValue
	private int Sid;
	private String Sname;
	private String Sstatus;
	
	
	

	@ManyToOne
	@JoinColumn(name="UserID")
	private SurveyUser surveyUser;
	
	@OneToMany(mappedBy="survey",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Question> question;

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSstatus() {
		return Sstatus;
	}

	public void setSstatus(String sstatus) {
		Sstatus = sstatus;
	}

	public SurveyUser getSurveyUser() {
		return surveyUser;
	}

	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}

	public Set<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Set<Question> question) {
		this.question = question;
	}

	
	

}
