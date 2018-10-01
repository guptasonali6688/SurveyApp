package com.zycus.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.entity.User;
import com.zycus.repository.UserRepository;
import com.zycus.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public void newUser(User user) {
		user.setRole(user.getRole().toLowerCase());
		userRepository.save(user);
	}

	public User validateUser(Map<String, String> user) {
		return userRepository.getUserFromIdPass(user.get("username"), user.get("password"));
	}

	public Iterable<User> getAllUser() {
		return userRepository.findByRole();
	}

	public Iterable<User> getUsersToShare(int survey_id) {
		return userRepository.getUsersToShare(survey_id);
	}

	public User getUser(int user_id) {
		return userRepository.getUserDetails(user_id);
	}
}
