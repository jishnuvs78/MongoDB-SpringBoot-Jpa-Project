package com.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class QueueProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		try {
			String xml=exchange.getIn().getBody(String.class);
			System.out.println(xml + xml.getClass());
			exchange.setProperty("payload",xml);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
