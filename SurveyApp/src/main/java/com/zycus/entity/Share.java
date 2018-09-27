package com.zycus.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHARE_TB")
public class Share {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "survey_id")
	private Survey survey;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private User admin;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Date date;

	private boolean responseStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(boolean responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Share [id=" + id + ", survey=" + survey + ", admin=" + admin + ", user=" + user + ", responseStatus="
				+ responseStatus + "]";
	}

}
