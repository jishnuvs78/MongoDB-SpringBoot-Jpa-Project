package com.camel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camel.model.Exchange;
import com.camel.model.Summary;
import com.camel.repository.ExchangeRepository;
import com.camel.repository.SummaryRepository;

@Service
public class BusinessService {
	
	@Autowired
	private SummaryRepository summaryRepository;
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
//	@Autowired
//	public BusinessService(SummaryRepository summaryRepository,ExchangeRepository exchangeRepository) {
//		this.summaryRepository=summaryRepository;
//		this.exchangeRepository=exchangeRepository;
//	}
	
	@Transactional
	public String insertToSummaryAndExchange(Summary summary,Exchange exchange) {
		
		try {
			Summary savedSummary=summaryRepository.save(summary);
			String summaryId=savedSummary.getId();
			exchange.setSummaryId(summaryId);
			Exchange savedExchange=exchangeRepository.save(exchange);
			return summaryId;
//			throw new Exception("Failed to Insert to DB: "+summaryId);
		}catch(Exception e) {
			throw new RuntimeException("Failed to Insert to DB: " + e.getMessage(), e);
		}
		
	}

}
