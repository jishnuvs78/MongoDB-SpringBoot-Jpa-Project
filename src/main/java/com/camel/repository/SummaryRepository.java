package com.camel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.camel.model.Summary;

@Repository
public interface SummaryRepository extends MongoRepository<Summary,String>{

}
