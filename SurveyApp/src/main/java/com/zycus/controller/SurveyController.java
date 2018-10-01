package com.zycus.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.dto.ResponseDTO;
import com.zycus.dto.ShareSurveyDTO;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.service.ResponseService;
import com.zycus.service.SurveyService;
import com.zycus.service.UserService;

@RestController
@RequestMapping("/survey/*")
public class SurveyController {

	@Autowired
	private SurveyService service;

	@Autowired
	UserService userService;

	@Autowired
	ResponseService responseService;

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public boolean userLogin(@RequestBody Map<String, String> userMap, HttpServletRequest request) {

		User user = userService.validateUser(userMap);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/addSurvey")
	public String addSurvey(@RequestBody Survey survey, HttpServletRequest request) {
		service.addSurvey(survey, request);
		return "Survey successfully created..";
	}

	@RequestMapping(value = "/shareSurveyUsers")
	public String addSurvey(@RequestBody ShareSurveyDTO shareSurveyDTO) {
		service.newShare(shareSurveyDTO);
		return "Successfully shared..";
	}

	@RequestMapping(value = "/deactivate", method = RequestMethod.POST)
	public String deactivate(@RequestBody Map<String, String> surveyMap) {
		service.deactivateSurvey(Integer.parseInt(surveyMap.get("id")));
		return "Survey successfully deactivated";
	}

	@RequestMapping(value = "/response", method = RequestMethod.POST)
	public String response(@RequestBody ResponseDTO responseDTO, HttpServletRequest request) {
		responseService.saveResponse(responseDTO, request);
		return "Thank you for taking the survey";
	}

}
