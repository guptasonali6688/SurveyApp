package com.zycus.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycus.dto.ShareSurveyDTO;
import com.zycus.entity.Question;
import com.zycus.entity.Response;
import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.repository.ShareSurveyRepository;
import com.zycus.repository.SurveyRepository;
import com.zycus.service.SurveyService;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private ShareSurveyRepository shareSurveyRepository;

	public void addSurvey(Survey survey, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		survey.setUser(user);
		Set<Question> questions = survey.getQuestions();
		for (Question q : questions) {
			q.setSurvey(survey);
		}
		surveyRepository.save(survey);
	}

	public Iterable<Survey> viewAllSurvey(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return surveyRepository.findAllSurvey(user.getId());

	}

	public Survey getSurvey(int id) {
		return surveyRepository.findById(id).get();
	}

	public Iterable<Survey> getActiveSurvey(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		return surveyRepository.getActiveSurvey(user.getId());
	}

	public void deactivateSurvey(int id) {
		surveyRepository.deactivateSurvey(id);
	}

	public void newShare(ShareSurveyDTO shareSurveyDTO) {
		Set<User> users = shareSurveyDTO.getUsers();
		System.out.println(shareSurveyDTO);

		for (User user : users) {
			Share share = new Share();
			share.setAdmin(shareSurveyDTO.getAdmin());
			share.setSurvey(shareSurveyDTO.getSurvey());
			share.setUser(user);
			share.setDate(new Date(System.currentTimeMillis()));
			System.out.println(share);

			share.setSurvey(surveyRepository.findById(share.getSurvey().getId()).get());
			shareSurveyRepository.save(share);
		}

	}

	public Map<String, String> getQueAns(Survey cur_survey, Iterable<Response> responses) {
		Map<String, String> qrmap = new HashMap<String, String>();
		Set<Question> questions = cur_survey.getQuestions();

		for (Question q : questions) {

			for (Response r : responses) {

				if (q.getId() == r.getQuestion().getId()) {
					qrmap.put(q.getQuestionText(), r.getAnswerText());
				}
			}
		}

		return qrmap;
	}
}
