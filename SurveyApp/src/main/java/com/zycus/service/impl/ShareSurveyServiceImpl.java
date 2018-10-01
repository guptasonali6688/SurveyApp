package com.zycus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.repository.ShareSurveyRepository;
import com.zycus.repository.SurveyRepository;
import com.zycus.repository.UserRepository;
import com.zycus.service.ShareSurveyService;

@Service
@Transactional
public class ShareSurveyServiceImpl implements ShareSurveyService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private ShareSurveyRepository shareSurveyRepository;

	public Iterable<Share> getSharedSurvey(int user_id) {
		return shareSurveyRepository.getSharedList(user_id);
	}

	public void setResponseStatus(int admin_id, int user_id, int survey_id) {
		shareSurveyRepository.setResponseStatus(admin_id, user_id, survey_id);
	}

	public List<Survey> getSurveyResponse(int id) {
		Iterable<Share> responseTrue = shareSurveyRepository.getAllSurveyResponse(id);
		List<Survey> surveys = new ArrayList<Survey>();

		for (Share share : responseTrue) {
			surveys.add(surveyRepository.findById(share.getSurvey().getId()).get());
		}

		return surveys;
	}

	public List<Survey> getSharedSurveyWithoutResponse(int user_id) {

		Iterable<Share> shareDetails = shareSurveyRepository.getSharedSurveyNoResponseGiven(user_id);
		List<Survey> surveys = new ArrayList<Survey>();

		for (Share share : shareDetails) {
			surveys.add(surveyRepository.findById(share.getSurvey().getId()).get());
		}

		return surveys;
	}

	public Iterable<Share> getSurveyWithResponse(int admin_id) {
		Iterable<Share> shareDetails = shareSurveyRepository.getAllResponse(admin_id);

		for (Share s : shareDetails) {
			s.setSurvey(surveyRepository.findById(s.getSurvey().getId()).get());
			s.setUser(userRepository.findById(s.getUser().getId()).get());
		}

		return shareDetails;
	}
}
