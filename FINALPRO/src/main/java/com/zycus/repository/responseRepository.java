package com.zycus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zycus.entity.response;

@Repository
public interface responseRepository extends CrudRepository<response,Integer> {

}
