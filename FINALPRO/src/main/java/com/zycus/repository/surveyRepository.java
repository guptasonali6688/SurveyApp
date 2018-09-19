package com.zycus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zycus.entity.Survey;

@Repository
public interface surveyRepository extends CrudRepository<Survey,Integer> {

}
