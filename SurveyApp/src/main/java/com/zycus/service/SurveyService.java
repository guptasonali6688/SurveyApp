package com.zycus.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zycus.dto.ShareSurveyDTO;
import com.zycus.entity.Response;
import com.zycus.entity.Survey;

public interface SurveyService {

	void addSurvey(Survey survey, HttpServletRequest request);

	Iterable<Survey> viewAllSurvey(HttpServletRequest request);

	Survey getSurvey(int id);

	Iterable<Survey> getActiveSurvey(HttpServletRequest request);

	void deactivateSurvey(int id);

	void newShare(ShareSurveyDTO shareSurveyDTO);

	Map<String, String> getQueAns(Survey survey, Iterable<Response> responses);

}
