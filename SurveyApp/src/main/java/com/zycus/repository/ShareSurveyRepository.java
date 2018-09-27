package com.zycus.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zycus.entity.Share;

public interface ShareSurveyRepository extends CrudRepository<Share, Integer> {

	@Query("select s from Share s where s.user.id = :user_id")
	Iterable<Share> getSharedList(@Param("user_id") int user_id);

	@Transactional
	@Modifying
	@Query("update Share s set s.responseStatus = true where (s.admin.id= :admin_id AND s.user.id = :user_id AND s.survey.id = :survey_id)")
	void setResponseStatus(@Param("admin_id") int admin_id, @Param("user_id") int user_id,
			@Param("survey_id") int survey_id);

	@Query("select s from Share s where (s.responseStatus = true AND s.user.id = :user_id)")
	Iterable<Share> getAllSurveyResponse(@Param("user_id") int id);

	@Query("select s from Share s where (s.user.id= :user_id AND s.responseStatus = false)")
	Iterable<Share> getSharedSurveyNoResponseGiven(@Param("user_id") int user_id);

	@Query("select s from Share s where  (s.responseStatus = true AND s.admin.id = :admin_id)")
	Iterable<Share> getAllResponse(@Param("admin_id") int admin_id);

}
