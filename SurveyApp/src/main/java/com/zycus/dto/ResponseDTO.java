package com.zycus.dto;

import java.util.List;

import com.zycus.entity.Response;
import com.zycus.entity.Survey;

public class ResponseDTO {

	private List<Response> responses;
	private Survey survey;

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
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
		return "ResponseDTO [responses=" + responses + ", survey=" + survey + "]";
	}

}
