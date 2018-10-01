package com.zycus.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zycus.entity.Response;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.service.ResponseService;
import com.zycus.service.ShareSurveyService;
import com.zycus.service.SurveyService;

@Controller
@RequestMapping("/surveyApp/*")
public class UserController {

	@Autowired
	SurveyService service;

	@Autowired
	ResponseService responseService;

	@Autowired
	ShareSurveyService shareService;

	@RequestMapping(value = "/allSurvey")
	public String viewSurvey(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		List<Survey> surveys = shareService.getSharedSurveyWithoutResponse(user.getId());

		model.addAttribute("surveys", surveys);
		model.addAttribute("user", user);

		return "user-surveylist";
	}

	@RequestMapping(value = "/view")
	public String viewSurvey(@RequestParam Map<String, String> surveyData, Model model, HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(surveyData.get("id")));

		model.addAttribute("user", request.getSession().getAttribute("user"));
		model.addAttribute("curSurvey", cur_survey);

		return "user-surveyview";
	}

	@RequestMapping(value = "/responses")
	public String viewResponses(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		List<Survey> surveys = shareService.getSurveyResponse(user.getId());

		model.addAttribute("surveys", surveys);
		model.addAttribute("user", user);

		return "user-surveyresponses";
	}

	@RequestMapping(value = "/responseview")
	public String viewSurveyResponse(@RequestParam Map<String, String> surveyData, Model model,
			HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(surveyData.get("id")));
		User user = (User) request.getSession().getAttribute("user");

		Iterable<Response> responses = responseService.getResponse(user.getId(), cur_survey.getId());

		Map<String, String> qrmap = service.getQueAns(cur_survey, responses);

		model.addAttribute("responses", responses);
		model.addAttribute("user", user);
		model.addAttribute("questionresponse", qrmap);
		model.addAttribute("curSurvey", cur_survey);

		return "user-surveyresponseview";
	}

}
