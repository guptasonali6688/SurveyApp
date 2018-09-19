package com.zycus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zycus.entity.Question;


@Repository
public interface questionRepository extends CrudRepository<Question,Integer> {

}
