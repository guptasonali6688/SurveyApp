package com.zycus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zycus.entity.Question;
import com.zycus.entity.Response;
import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.service.SurveyService;

@Controller
@RequestMapping("/surveyApp/*")
public class UserController {

	@Autowired
	SurveyService service;

	@RequestMapping(value = "/allSurvey")
	public String viewSurvey(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		Iterable<Share> shareDetails = service.getSharedSurveyWithoutResponse(user.getId());
		List<Survey> surveys = new ArrayList<Survey>();
		for (Share share : shareDetails) {
			surveys.add(service.getSurvey(share.getSurvey().getId()));
		}
		System.out.println(surveys.size());
		model.addAttribute("surveys", surveys);
		return "user-surveylist";
	}

	@RequestMapping(value = "/view")
	public String viewSurvey(@RequestParam Map<String, String> surveyData, Model model, HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(surveyData.get("id")));
		System.out.println(cur_survey.getQuestions().size());
		model.addAttribute("user", request.getSession().getAttribute("user"));
		model.addAttribute("curSurvey", cur_survey);
		return "user-surveyview";
	}

	@RequestMapping(value = "/responses")
	public String viewResponses(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		Iterable<Share> responseTrue = service.getSurveyResponse(user.getId());
		List<Survey> surveys = new ArrayList<Survey>();
		for (Share share : responseTrue) {
			surveys.add(service.getSurvey(share.getSurvey().getId()));
		}
		model.addAttribute("surveys", surveys);

		return "user-surveyresponses";
	}

	@RequestMapping(value = "/responseview")
	public String viewSurveyResponse(@RequestParam Map<String, String> surveyData, Model model,
			HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(surveyData.get("id")));
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Response> responses = service.getResponse(user.getId(), cur_survey.getId());
		model.addAttribute("responses", responses);
		System.out.println(cur_survey.getQuestions().size());
		Map<String, String> qrmap = new HashMap<String, String>();
		Set<Question> questions = cur_survey.getQuestions();
		for (Question q : questions) {
			for (Response r : responses) {
				if (q.getId() == r.getQuestion().getId()) {
					qrmap.put(q.getQuestionText(), r.getAnswerText());
				}
			}
		}
		System.out.println(user.getId());
		System.out.println(cur_survey.getId());
		model.addAttribute("user", user);
		model.addAttribute("questionresponse", qrmap);
		model.addAttribute("curSurvey", cur_survey);
		return "user-surveyresponseview";
	}

}
