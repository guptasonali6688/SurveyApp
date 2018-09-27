package com.zycus.dto;

import java.util.Set;

import com.zycus.entity.Survey;
import com.zycus.entity.User;

public class ShareSurveyDTO {

	private User admin;
	private Set<User> users;
	private Survey survey;

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "ShareSurveyDTO [admin=" + admin + ", users=" + users + ", survey=" + survey + "]";
	}

}
