package com.zycus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.DTO.UserDetail;
import com.zycus.entity.SurveyUser;
import com.zycus.service.SurveyServiceImpl;



public class LoginController {

	@Autowired
	private SurveyServiceImpl SurveyServiceImpl;


	/*@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")

	public String LoginMethod(@RequestBody  UserDetail userDetail)
	{

		SurveyServiceImpl.placeOrder(userDetail);
		return "You are getting logged in";
		
		
	}*/
	
	
	
}
