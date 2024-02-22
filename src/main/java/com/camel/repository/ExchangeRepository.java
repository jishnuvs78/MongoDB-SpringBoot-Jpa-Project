package com.camel.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.camel.model.Exchange;

public interface ExchangeRepository extends MongoRepository<Exchange,String>{
	
	Optional<Exchange> findBySummaryId(String summaryId);

}
