package com.camel.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.camel.model.Exchange;

@Repository
public interface ExchangeRepository extends MongoRepository<Exchange,String>{
	
	Optional<Exchange> findBySummaryId(String summaryId);

}
