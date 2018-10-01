package com.zycus.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycus.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select u from User u where u.username = :username and u.password = :password")
	public User getUserFromIdPass(@Param("username") String username, @Param("password") String password);

	@Query("select u from User u where u.role = 'user' OR u.role = 'User'")
	public Iterable<User> findByRole();

	@Query("select u from User u where (u.role != 'admin' AND u.id not in (select s.user.id from Share s where s.survey.id = :survey_id))")
	public Iterable<User> getUsersToShare(@Param("survey_id") int survey_id);

	@Query("select u from User u where u.id = :user_id")
	public User getUserDetails(@Param("user_id") int user_id);

}
