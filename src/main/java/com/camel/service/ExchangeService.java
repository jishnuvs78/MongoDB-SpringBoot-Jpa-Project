package com.camel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camel.model.Exchange;
import com.camel.repository.ExchangeRepository;

@Service
public class ExchangeService {
	
	private final ExchangeRepository repository;
	
	@Autowired
	public ExchangeService(ExchangeRepository repository) {
		this.repository=repository;
	}
	
	public String insertExchange(Exchange exchange) {
		try {
			Exchange savedExchange=repository.save(exchange);
			return "Record Saved";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public Optional<Exchange> getExchangeBySummaryId(String summaryId) {
		return repository.findBySummaryId(summaryId);
	}

}
