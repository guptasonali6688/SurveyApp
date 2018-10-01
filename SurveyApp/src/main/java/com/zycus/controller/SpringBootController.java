package com.zycus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zycus.entity.User;
import com.zycus.exception.UserNotFoundException;
import com.zycus.service.SurveyService;
import com.zycus.service.UserService;

@Controller
@RequestMapping("/*")
public class SpringBootController {

	@Autowired
	SurveyService service;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET, produces = "text/plain")
	public String newUser(User user) {
		return "newUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "newUser";
		} else {
			userService.newUser(user);
			model.addAttribute("message", "Registered successfully..");

			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/plain")
	public String login() {
		return "login-view";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET, produces = "text/plain")
	public String home() {
		return "home.html";
	}

	@RequestMapping("/survey/home")
	public String goHome(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			model.addAttribute("user", user);
			if (user.getRole().equalsIgnoreCase("ADMIN")) {
				return "admin-home";
			} else {
				return "user-home";
			}
		} else {
			throw new UserNotFoundException("No Such User");
		}

	}
}
