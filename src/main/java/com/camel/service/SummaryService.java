package com.camel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camel.model.Summary;
import com.camel.repository.SummaryRepository;

@Service
public class SummaryService {
	
	private final SummaryRepository repository;
	
	@Autowired
	public SummaryService(SummaryRepository repository) {
		this.repository=repository;
	}
	
	@Transactional
	public String insertSummary(Summary summary) {
		Summary savedSummary=repository.save(summary);
//		return savedSummary.getId();
		throw new RuntimeException("Failed to create employee: ");
	}

}
