package com.zycus.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.DTO.UserDetail;
import com.zycus.entity.Question;
import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;
import com.zycus.service.SurveyServiceImpl;


@RestController
@RequestMapping("/Survey/*")
public class AdminController {
	
	
	@Autowired
	private SurveyServiceImpl SurveyServiceImpl;
	
	@RequestMapping(value = "/newAdmin", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 



	public String add(@RequestBody  SurveyUser user)
	{

		SurveyServiceImpl.registerUser(user);
		return "User record created successfully";
		
		
	}
	
	
	@RequestMapping(value = "/newSurvey", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	
	public String add(@RequestBody Survey survey, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		SurveyUser user = (SurveyUser)session.getAttribute("user");
		survey.setSurveyUser(user);
		System.out.println(survey.getQuestion().toString());
		SurveyServiceImpl.registerSurvey(survey);
		return "Survey created successfully";
	}
	
	
}
