package com.camel.processors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.camel.config.CustomRetryException;

public class HeadersProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		UUID uuid=UUID.randomUUID();
		String uuidStr=uuid.toString();
		
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>(exchange.getIn().getHeaders());
		headers.put("exceptionMessage","");
		headers.put("hermesId",uuidStr);
		if(headers.containsKey("retryCount")) {
			String countStr=(String) headers.get("retryCount");
			Integer count= Integer.parseInt(countStr)+1;
			countStr=count.toString();
			headers.put("retryCount",countStr);
		}else {
			headers.put("retryCount","0");
		}
		String currTime=GenerateTimestamp();
		headers.put("initialTimestamp",currTime);
		headers.put("currentTimestamp",currTime);
		headers.put("exceptionMessage","Retryable/NonRetryable");
		System.out.println("headers map:"+headers);
		exchange.getIn().setHeaders(headers);
		
		throw new CustomRetryException("General Exception 2");
		
	}
	
	private String GenerateTimestamp() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
		String formattedTimestamp = currentDateTime.format(formatter);
		return formattedTimestamp;
	}
	
	

}
