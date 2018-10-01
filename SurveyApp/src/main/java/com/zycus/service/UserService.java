package com.zycus.service;

import java.util.Map;

import com.zycus.entity.User;

public interface UserService {

	void newUser(User user);

	User validateUser(Map<String, String> user);

	Iterable<User> getAllUser();

	Iterable<User> getUsersToShare(int survey_id);

	User getUser(int user_id);
}
