package com.camel.service;

import java.util.Optional;

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
		
		try {
			Summary savedSummary=repository.save(summary);
			return savedSummary.getId();
		}catch(Exception e) {
			throw new RuntimeException("Failed to create employee: "+e.getMessage(), e);
		}		
		
	}
	
	public Summary getSummaryById (String Id){
		return repository.findById(Id).orElse(null);
	}
	
	public void updateSummary (String Id,Summary newSummary,Boolean flag) {
		Summary existingSummary=repository.findById(Id).orElse(null);
		
		if(flag==true) {
			existingSummary.setFraudStatus(newSummary.getFraudStatus());
		}else {
			existingSummary.setSanctionsFlag(newSummary.getSanctionsFlag());
		}
		
		repository.save(existingSummary);		
		
	}
	
	public void setFraudFlag (String Id) {
		
		Summary existingSummary=repository.findById(Id).orElse(null);
		
		String fraudFlag=existingSummary.getFraudStatus();
		String sanctionsFlag=existingSummary.getSanctionsFlag();
		
		if(fraudFlag=="true" || sanctionsFlag=="true") {
			existingSummary.setFinalFlag("true");
		}else {
			existingSummary.setFinalFlag("false");
		}
		
		repository.save(existingSummary);
	}

}
