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
public class User {
	

	@Id
	private int id;
	private String name;
	private String email;
	private String password;
	
	
	

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Set<Survey> survey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Survey> getSurvey() {
		return survey;
	}

	public void setSurvey(Set<Survey> survey) {
		this.survey = survey;
	}
	
	
	
	
	

}
