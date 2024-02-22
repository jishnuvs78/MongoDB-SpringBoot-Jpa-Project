package com.camel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.camel.model.Summary;

public interface SummaryRepository extends MongoRepository<Summary,String>{

}
