
package com.zycus.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zycus.entity.Survey;

public interface SurveyRepository extends CrudRepository<Survey, Integer> {

	@Query("select s from Survey s where (s.status = true AND s.user.id = :user_id )")
	public Iterable<Survey> getActiveSurvey(@Param("user_id") int user_id);

	@Transactional
	@Modifying
	@Query("update Survey s set s.status = false where s.id = :id")
	public void deactivateSurvey(@Param("id") int id);

	@Query("select s from Survey s where s.user.id = :user_id")
	public Iterable<Survey> findAllSurvey(@Param("user_id") int user_id);

}
