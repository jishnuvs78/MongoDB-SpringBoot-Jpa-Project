package com.camel.processors;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import com.camel.model.Message;
import com.camel.service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformPayloadProcessor implements Processor {
	
	private final ExchangeService exchangeService;
	
	@Autowired
	public TransformPayloadProcessor(ExchangeService exchangeService) {
		this.exchangeService=exchangeService;
	} 
	
	private void stringToJson(String xml,String recordId) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
			Message payload = (Message)jaxbUnmarshaller.unmarshal(new StringReader(xml));
			
			System.out.println(payload);
			
			ObjectMapper obj=new ObjectMapper();
			
			try {
				
				String jsonPayload=obj.writeValueAsString(payload);
				System.out.println(jsonPayload);
				
				com.camel.model.Exchange newExchange = new com.camel.model.Exchange();
				newExchange.setSummaryId(recordId);
				newExchange.setDesc("Payload Transformed");
				newExchange.setStgId("2");
				newExchange.setPayload(jsonPayload);
				
				String ot=exchangeService.insertExchange(newExchange);
				System.out.println("Status: " + ot);
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String recordId=exchange.getIn().getBody(String.class);
		System.out.println(recordId);
		
		Optional<com.camel.model.Exchange> fetchedRecord=exchangeService.getExchangeBySummaryId(recordId);
		fetchedRecord.ifPresentOrElse(
				r-> stringToJson(r.getPayload(), recordId), 
				()-> System.out.println("Record not found")
				);
		
	}

}
