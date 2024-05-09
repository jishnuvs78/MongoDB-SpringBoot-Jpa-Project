package com.camel.processors;

import java.util.LinkedHashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RetryProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		LinkedHashMap<String, Object> headers = new LinkedHashMap<>(exchange.getIn().getHeaders());
		if(headers.containsKey("hermesId")) {
			System.out.println("Old msg");
		}else {
			System.out.println("New msg");
		}
		
	}

}
