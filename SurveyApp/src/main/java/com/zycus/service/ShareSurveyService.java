package com.zycus.service;

import java.util.List;

import com.zycus.entity.Share;
import com.zycus.entity.Survey;

public interface ShareSurveyService {

	Iterable<Share> getSharedSurvey(int user_id);

	void setResponseStatus(int admin_id, int user_id, int survey_id);

	List<Survey> getSurveyResponse(int id);

	List<Survey> getSharedSurveyWithoutResponse(int user_id);

	Iterable<Share> getSurveyWithResponse(int admin_id);

}
