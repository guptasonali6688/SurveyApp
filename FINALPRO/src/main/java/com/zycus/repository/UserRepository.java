package com.zycus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zycus.entity.SurveyUser;

@Repository
public interface UserRepository extends CrudRepository<SurveyUser,Integer> {

}
