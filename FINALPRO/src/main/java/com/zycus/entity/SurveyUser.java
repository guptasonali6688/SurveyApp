package com.zycus.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TBL_SURVEYUSER")
public class SurveyUser {
	

	@Id
	@GeneratedValue
	private int Uid;
	private String Uname;
	private String Uemail;
	private String Upassword;
	private String Urole;

	@Override
	public String toString() {
		return "SurveyUser [Uid=" + Uid + ", Uname=" + Uname + ", Uemail=" + Uemail + ", Upassword=" + Upassword
				+ ", Urole=" + Urole + ", survey=" + survey + ", responses=" + responses + "]";
	}

	@OneToMany(mappedBy="surveyUser",cascade=CascadeType.ALL)
	private Set<Survey> survey;
	
	@OneToMany
	private Set<response> responses;

	
	public int getUid() {
		return Uid;
	}

	public void setUid(int uid) {
		Uid = uid;
	}

	public String getUname() {
		return Uname;
	}

	public void setUname(String uname) {
		Uname = uname;
	}

	public String getUemail() {
		return Uemail;
	}

	public void setUemail(String uemail) {
		Uemail = uemail;
	}

	public String getUpassword() {
		return Upassword;
	}

	public void setUpassword(String upassword) {
		Upassword = upassword;
	}

	public String getUrole() {
		return Urole;
	}

	public void setUrole(String urole) {
		Urole = urole;
	}

	public Set<Survey> getSurvey() {
		return survey;
	}

	public void setSurvey(Set<Survey> survey) {
		this.survey = survey;
	}

	public Set<response> getResponses() {
		return responses;
	}

	public void setResponses(Set<response> responses) {
		this.responses = responses;
	}

	
	
	
	
	
	

}
