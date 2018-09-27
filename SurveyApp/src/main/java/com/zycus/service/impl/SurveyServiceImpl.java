package com.zycus.service.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycus.entity.Question;
import com.zycus.entity.Response;
import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.repository.ResponseRepository;
import com.zycus.repository.ShareSurveyRepository;
import com.zycus.repository.SurveyRepository;
import com.zycus.repository.UserRepository;
import com.zycus.service.SurveyService;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private ShareSurveyRepository shareSurveyRepository;

	@Autowired
	private ResponseRepository responseRepository;

	public void newUser(User user) {
		userRepository.save(user);
	}

	public User validateUser(Map<String, String> user) {
		return userRepository.getUserFromIdPass(user.get("username"), user.get("password"));
	}

	public void addSurvey(Survey survey) {
		Set<Question> questions = survey.getQuestions();
		for (Question q : questions) {
			q.setSurvey(survey);
		}
		surveyRepository.save(survey);
	}

	public Iterable<Survey> viewAllSurvey(int user_id) {
		return surveyRepository.findAllSurvey(user_id);

	}

	public Survey getSurvey(int id) {
		return surveyRepository.findById(id).get();
	}

	public Iterable<Survey> getActiveSurvey(int user_id) {
		return surveyRepository.getActiveSurvey(user_id);
	}

	public void deactivateSurvey(Survey survey) {
		surveyRepository.deactivateSurvey(survey.getId());
	}

	public Iterable<User> getAllUser() {
		return userRepository.findByRole();
	}

	public void newShare(Share share) {
		share.setSurvey(surveyRepository.findById(share.getSurvey().getId()).get());
		shareSurveyRepository.save(share);
	}

	public Iterable<Share> getSharedSurvey(int user_id) {
		return shareSurveyRepository.getSharedList(user_id);
	}

	public void saveResponse(Response singleRes) {
		responseRepository.save(singleRes);
	}

	public void setResponseStatus(int admin_id, int user_id, int survey_id) {
		shareSurveyRepository.setResponseStatus(admin_id, user_id, survey_id);
	}

	public Iterable<Share> getSurveyResponse(int id) {
		return shareSurveyRepository.getAllSurveyResponse(id);
	}

	public Iterable<User> getUsersToShare(int survey_id) {
		return userRepository.getUsersToShare(survey_id);
	}

	public Iterable<Response> getResponse(int user_id, int survey_id) {
		return responseRepository.getResponseSurvey(user_id, survey_id);
	}

	public Iterable<Share> getSharedSurveyWithoutResponse(int user_id) {
		return shareSurveyRepository.getSharedSurveyNoResponseGiven(user_id);
	}

	public Iterable<Share> getSurveyWithResponse(int admin_id) {
		return shareSurveyRepository.getAllResponse(admin_id);
	}
}
