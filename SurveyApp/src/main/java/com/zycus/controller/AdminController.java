package com.zycus.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zycus.entity.Question;
import com.zycus.entity.Response;
import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.service.SurveyService;

@Controller
@RequestMapping("/home/*")
public class AdminController {

	@Autowired
	SurveyService service;

	@RequestMapping(value = "/survey")
	public String newSurvey() {

		return "newSurvey";
	}

	@RequestMapping(value = "/view")
	public String viewSurvey(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Survey> surveys = service.viewAllSurvey(user.getId());
		model.addAttribute("surveys", surveys);
		return "view-survey";
	}

	@PostMapping(value = "/SurveyDetails")
	public String surveyDetails(@RequestParam Map<String, String> body, Model model) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(body.get("id")));
		System.out.println(cur_survey.getQuestions().size());
		model.addAttribute("curSurvey", cur_survey);
		return "show-survey";
	}

	@RequestMapping(value = "/deactivateSurvey")
	public String deactivateSurvey(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Survey> surveys = service.getActiveSurvey(user.getId());
		model.addAttribute("surveys", surveys);
		return "deactivate-survey";
	}

	@RequestMapping(value = "/shareSurvey")
	public String shareSurvey(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Survey> surveys = service.viewAllSurvey(user.getId());
		model.addAttribute("surveys", surveys);
		return "share-survey";
	}

	@PostMapping(value = "/share")
	public String shareToUser(@RequestParam Map<String, String> body, Model model, HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(body.get("id")));
		Iterable<User> users = service.getUsersToShare(cur_survey.getId());
		/*
		 * List<User> userList = new ArrayList<User>(); for (User user : users)
		 * { if (!service.checkShareData(user.getId(), cur_survey.getId())) {
		 * userList.add(user); } }
		 */
		System.out.println(users);

		System.out.println(cur_survey.getQuestions().size());
		model.addAttribute("curSurvey", cur_survey);
		model.addAttribute("users", users);
		model.addAttribute("admin", request.getSession().getAttribute("user"));
		return "share-view";
	}

	@RequestMapping(value = "/responses")
	public String responses(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Share> shareDetails = service.getSurveyWithResponse(user.getId());

		for (Share s : shareDetails) {
			s.setSurvey(service.getSurvey(s.getSurvey().getId()));
		}

		model.addAttribute("shareData", shareDetails);
		return "response-view";
	}

	@RequestMapping(value = "/responseview")
	public String viewSurveyResponse(@RequestParam Map<String, String> surveyData, Model model,
			HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(surveyData.get("id")));
		Iterable<Response> responses = service.getResponse(Integer.parseInt(surveyData.get("user_id")),
				cur_survey.getId());
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
		System.out.println(cur_survey.getId());
		model.addAttribute("questionresponse", qrmap);
		model.addAttribute("curSurvey", cur_survey);
		return "response-show";
	}

	@RequestMapping(value = "/responsefilter")
	public String responsesfilter(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Share> shareDetails = service.getSurveyWithResponse(user.getId());

		for (Share s : shareDetails) {
			s.setSurvey(service.getSurvey(s.getSurvey().getId()));
		}

		model.addAttribute("shareData", shareDetails);
		return "response-viewfilter";
	}

}
