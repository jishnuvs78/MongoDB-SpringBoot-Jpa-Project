package com.camel.processors;

import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import com.camel.model.Summary;
import com.camel.service.SummaryService;

public class MockRespProcessor implements Processor {
	
	
	private SummaryService summaryService;
	
	@Autowired
	public MockRespProcessor(SummaryService summaryService) {
		this.summaryService=summaryService;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		try {
	
//			String id=exchange.getProperty("summaryId",String.class);
			String id=exchange.getIn().getBody(String.class);
			Summary newSummary = new Summary();
			newSummary.setFraudStatus("false");
			
			summaryService.updateSummary(id, newSummary,true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
