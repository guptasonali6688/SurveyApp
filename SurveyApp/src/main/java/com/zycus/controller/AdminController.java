package com.zycus.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zycus.entity.Response;
import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.service.ResponseService;
import com.zycus.service.ShareSurveyService;
import com.zycus.service.SurveyService;
import com.zycus.service.UserService;

@Controller
@RequestMapping("/home/*")
public class AdminController {

	@Autowired
	SurveyService service;

	@Autowired
	UserService userService;

	@Autowired
	ResponseService responseService;

	@Autowired
	ShareSurveyService shareService;

	@RequestMapping(value = "/survey")
	public String newSurvey() {

		return "newSurvey";
	}

	@RequestMapping(value = "/view")
	public String viewSurvey(Model model, HttpServletRequest request) {
		Iterable<Survey> surveys = service.viewAllSurvey(request);
		model.addAttribute("surveys", surveys);

		return "view-survey";
	}

	@PostMapping(value = "/SurveyDetails")
	public String surveyDetails(@RequestParam Map<String, String> body, Model model) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(body.get("id")));
		model.addAttribute("curSurvey", cur_survey);

		return "show-survey";
	}

	@RequestMapping(value = "/deactivateSurvey")
	public String deactivateSurvey(Model model, HttpServletRequest request) {
		Iterable<Survey> surveys = service.getActiveSurvey(request);
		model.addAttribute("surveys", surveys);

		return "deactivate-survey";
	}

	@RequestMapping(value = "/shareSurvey")
	public String shareSurvey(Model model, HttpServletRequest request) {
		Iterable<Survey> surveys = service.viewAllSurvey(request);
		model.addAttribute("surveys", surveys);

		return "share-survey";
	}

	@PostMapping(value = "/share")
	public String shareToUser(@RequestParam Map<String, String> body, Model model, HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(body.get("id")));
		Iterable<User> users = userService.getUsersToShare(cur_survey.getId());

		model.addAttribute("curSurvey", cur_survey);
		model.addAttribute("users", users);
		model.addAttribute("admin", request.getSession().getAttribute("user"));

		return "share-view";
	}

	@RequestMapping(value = "/responses")
	public String responses(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Share> shareDetails = shareService.getSurveyWithResponse(user.getId());

		model.addAttribute("shareData", shareDetails);
		return "response-view";
	}

	@RequestMapping(value = "/responseview")
	public String viewSurveyResponse(@RequestParam Map<String, String> surveyData, Model model,
			HttpServletRequest request) {
		Survey cur_survey = service.getSurvey(Integer.parseInt(surveyData.get("id")));
		Iterable<Response> responses = responseService.getResponse(Integer.parseInt(surveyData.get("user_id")),
				cur_survey.getId());

		Map<String, String> qrmap = service.getQueAns(cur_survey, responses);

		model.addAttribute("responses", responses);
		model.addAttribute("questionresponse", qrmap);
		model.addAttribute("curSurvey", cur_survey);

		return "response-show";
	}

	@RequestMapping(value = "/responsefilter")
	public String responsesfilter(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Iterable<Share> shareDetails = shareService.getSurveyWithResponse(user.getId());

		model.addAttribute("shareData", shareDetails);
		return "response-viewfilter";
	}

}
