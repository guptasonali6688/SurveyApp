package com.zycus.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycus.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>{

}
