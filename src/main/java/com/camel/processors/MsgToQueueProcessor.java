package com.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MsgToQueueProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String body=exchange.getProperty("msg",String.class);
		System.out.println("body: "+body);
		exchange.getIn().setBody(body);
		
	}

}
