package com.zycus.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycus.entity.Response;

public interface ResponseRepository extends CrudRepository<Response, Integer> {

	/*
	 * @Query("select r from Response r where (r.question.id  IN (select q.id from Question q ) AND r.user.id= :user_id AND r.survey.id = :survey_id)"
	 * ) Iterable<Response> getResponseSurvey(@Param("user_id") int
	 * user_id, @Param("survey_id") int survey_id);
	 */

	@Query("select r from Response r where (r.user.id= :user_id AND r.survey.id = :survey_id)")
	Iterable<Response> getResponseSurvey(@Param("user_id") int user_id, @Param("survey_id") int survey_id);

}
