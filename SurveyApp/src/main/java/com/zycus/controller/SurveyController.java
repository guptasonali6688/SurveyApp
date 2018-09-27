package com.zycus.controller;

import java.sql.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.dto.ResponseDTO;
import com.zycus.dto.ShareSurveyDTO;
import com.zycus.entity.Response;
import com.zycus.entity.Share;
import com.zycus.entity.Survey;
import com.zycus.entity.User;
import com.zycus.service.SurveyService;

@RestController
@RequestMapping("/survey/*")
public class SurveyController {

	@Autowired
	private SurveyService service;
	/*
	 * @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes
	 * = "application/json", produces = "text/plain") public String
	 * addUser(@RequestBody User user) { service.newUser(user); return
	 * "Registered successfully"; }
	 */

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public boolean userLogin(@RequestBody Map<String, String> userMap, HttpServletRequest request) {

		User user = service.validateUser(userMap);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/addSurvey")
	public String addSurvey(@RequestBody Survey survey, HttpServletRequest request) {
		System.out.println(survey);

		User user = (User) request.getSession().getAttribute("user");
		survey.setUser(user);

		service.addSurvey(survey);
		return "Survey successfully created..";
	}

	@RequestMapping(value = "/shareSurveyUsers")
	public String addSurvey(@RequestBody ShareSurveyDTO shareSurveyDTO, HttpServletRequest request) {

		Set<User> users = shareSurveyDTO.getUsers();
		System.out.println(shareSurveyDTO);

		for (User user : users) {
			Share share = new Share();
			share.setAdmin(shareSurveyDTO.getAdmin());
			share.setSurvey(shareSurveyDTO.getSurvey());
			share.setUser(user);
			share.setDate(new Date(System.currentTimeMillis()));
			System.out.println(share);

			// TODO: Save the share data
			service.newShare(share);
		}
		return "Successfully shared..";
	}

	@RequestMapping(value = "/deactivate", method = RequestMethod.POST)
	public String deactivate(@RequestBody Map<String, String> surveyMap) {
		System.out.println(surveyMap.size());
		Survey survey = service.getSurvey(Integer.parseInt(surveyMap.get("id")));
		service.deactivateSurvey(survey);
		return "Survey successfully deactivated";
	}

	@RequestMapping(value = "/response", method = RequestMethod.POST)
	public String response(@RequestBody ResponseDTO responseDTO, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		// System.out.println(responseDTO);
		System.out.println("User Obj: " + user);
		// System.out.println("Survey Obj: " + survey);
		service.setResponseStatus(responseDTO.getSurvey().getUser().getId(), user.getId(),
				responseDTO.getSurvey().getId());

		for (Response res : responseDTO.getResponses()) {
			System.out.println(res);
			service.saveResponse(res);
		}

		return "Thank you for taking the survey";
	}

}
