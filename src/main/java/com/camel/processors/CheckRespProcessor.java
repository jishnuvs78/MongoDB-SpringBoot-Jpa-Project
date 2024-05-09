package com.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import com.camel.model.Summary;
import com.camel.service.SummaryService;

public class CheckRespProcessor implements Processor {

	private SummaryService summaryService;
	
	@Autowired
	public CheckRespProcessor(SummaryService summaryService) {
		this.summaryService=summaryService;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String id=exchange.getIn().getBody(String.class);
		Summary existingSummary = summaryService.getSummaryById(id);
		
		exchange.setProperty("flag", existingSummary.getFinalFlag());
		
	}

}
