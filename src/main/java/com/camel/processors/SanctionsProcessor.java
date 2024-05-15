package com.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.model.Summary;
import com.camel.service.SummaryService;

@Component
public class SanctionsProcessor implements Processor {
	
	@Autowired
	private SummaryService summaryService;
	
//	@Autowired
//	public SanctionsProcessor(SummaryService summaryService) {
//		this.summaryService=summaryService;
//	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		try {
			System.out.println(exchange.getIn().getBody(String.class));
			Summary newSummary = new Summary();
			newSummary.setSanctionsFlag("false");
			
			summaryService.updateSummary(exchange.getProperty("summaryId",String.class), newSummary,false);
			summaryService.setFraudFlag(exchange.getProperty("summaryId",String.class));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
