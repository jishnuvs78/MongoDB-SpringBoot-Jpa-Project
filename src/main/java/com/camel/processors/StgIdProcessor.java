package com.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import com.camel.service.ExchangeService;

public class StgIdProcessor implements Processor{
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	public StgIdProcessor(ExchangeService exchangeService) {
		this.exchangeService=exchangeService;
	}
	
	public void setStgId(String stgId,String status,String recordId,Exchange exchange) {
		
		try {
			com.camel.model.Exchange newExchange = new com.camel.model.Exchange();
			newExchange.setSummaryId(recordId);
			newExchange.setDesc(status);
			newExchange.setStgId(stgId);
			newExchange.setPayload(exchange.getProperty("payload",String.class));
			
			String ot=exchangeService.insertExchange(newExchange);
			System.out.println("Status: " + ot);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String stgId=exchange.getProperty("stgId",String.class);
		String status=exchange.getProperty("status",String.class);
		String summaryId=exchange.getProperty("summaryId",String.class);
		
		setStgId(stgId, status, summaryId, exchange);
	}

}
