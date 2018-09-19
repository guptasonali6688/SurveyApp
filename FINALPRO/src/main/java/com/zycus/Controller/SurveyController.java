
package com.zycus.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.DTO.UserDetail;
import com.zycus.entity.Question;
import com.zycus.entity.Survey;
import com.zycus.entity.SurveyUser;
import com.zycus.entity.response;
import com.zycus.service.*;

@RestController
@RequestMapping("/Survey/*")
public class SurveyController {
	
	@Autowired
	private SurveyServiceImpl SurveyServiceImpl;
	
	
	
	
	@RequestMapping(value = "/newUser", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 
	public String add(@RequestBody  SurveyUser user)
	{
		
		System.out.println(user.toString());
		SurveyServiceImpl.registerUser(user);
		return "User record created successfully";	
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String LoginMethod(@RequestBody  SurveyUser surveyUser, HttpServletRequest request)
	{
		
		HttpSession session = request.getSession();
		System.out.println(surveyUser);
		
		if(SurveyServiceImpl.SurveyLogIn(surveyUser)==1)
		{
			session.setAttribute("user",surveyUser);
		System.out.println("hgb");
			return "admin";
		}
		else if(SurveyServiceImpl.SurveyLogIn(surveyUser)==2)
		{
			session.setAttribute("user",surveyUser);
			return "user";
		}
		else
		{
			session.setAttribute("user",surveyUser);
			return "fail";
		}
		
		
		
		
	}
	
	@RequestMapping(value = "/loginget", method = RequestMethod.GET, produces = "text/plain")
	public String Sayhello()
	{
		return "Hello";
	}
	
	
	/*@RequestMapping(value = "/newSurvey", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 



	public String add(@RequestBody  Survey survey)
	{

		SurveyServiceImpl.registerSurvey(survey);
		return "Survey record created successfully";
	}

	
	@RequestMapping(value = "/newQuestion", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 



	public String add(@RequestBody  Question question)
	{

		SurveyServiceImpl.registerQuestion(question);
		return "Question record created successfully";
	}

	
	@RequestMapping(value = "/newResponse", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain") 



	public String add(@RequestBody  response response)
	{

		SurveyServiceImpl.registerresponse(response);
		return "response record created successfully";
	}
*/
	
/*	
	
@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
	
	public Iterable<Customer> getAllCustomers() 
	{
		
		
		return shoppingService.getRegisterdCustomers();
	}

@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")

public Iterable<Product> getAllProducts() 
{
	
	
	return shoppingService.getRegisterdProducts();
}

*/

}
