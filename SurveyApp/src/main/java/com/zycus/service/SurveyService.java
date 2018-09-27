package com.zycus.service;

import java.util.Map;

import com.zycus.entity.Response;
import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.entity.User;

public interface SurveyService {

	void newUser(User user);

	User validateUser(Map<String, String> user);

	void addSurvey(Survey survey);

	Iterable<Survey> viewAllSurvey(int user_id);

	Survey getSurvey(int id);

	Iterable<Survey> getActiveSurvey(int user_id);

	void deactivateSurvey(Survey survey);

	Iterable<User> getAllUser();

	void newShare(Share share);

	Iterable<Share> getSharedSurvey(int user_id);

	void saveResponse(Response singleRes);

	void setResponseStatus(int admin_id, int user_id, int survey_id);

	Iterable<Share> getSurveyResponse(int id);

	Iterable<User> getUsersToShare(int survey_id);

	Iterable<Response> getResponse(int user_id, int survey_id);

	Iterable<Share> getSharedSurveyWithoutResponse(int user_id);

	Iterable<Share> getSurveyWithResponse(int admin_id);

}
