package com.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.camel.model.MyDocument;
import com.camel.model.Summary;
import com.camel.service.ExchangeService;
import com.camel.service.MyDocumentService;
import com.camel.service.SummaryService;

public class SetPayloadProcessor implements Processor {
	
	private final SummaryService summaryService;
	private final ExchangeService exchangeService;
	
	@Autowired
	public SetPayloadProcessor(SummaryService summaryService,ExchangeService exchangeService) {
		this.summaryService=summaryService;
		this.exchangeService=exchangeService;
	}
	
	public static class CustomNonRollbackException extends RuntimeException {
        public CustomNonRollbackException(String message) {
            super(message);
        }
    }
	
	@Override 
	public void process(Exchange exchange) throws Exception {
		String summaryId="";
		try {
			Summary summary = new Summary();
			summary.setMessageType("xml");
			summaryId=summaryService.insertSummary(summary);
			System.out.println("Inserted summary ID: " + summaryId);
		}catch(Exception e) {
			System.err.println("Failed to create employee: " + e.getMessage());
		}
		com.camel.model.Exchange newExchange = new com.camel.model.Exchange();
		newExchange.setSummaryId(summaryId);
		newExchange.setDesc("Payload Recieved");
		newExchange.setStgId("1");
		newExchange.setPayload(exchange.getProperty("payload",String.class));
		String ot=exchangeService.insertExchange(newExchange);
		System.out.println("Status: " + ot);
		
	}
	
	

}
