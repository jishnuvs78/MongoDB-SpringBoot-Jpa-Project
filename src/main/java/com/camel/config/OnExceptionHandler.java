package com.camel.config;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OnExceptionHandler {
	
	public static void handle(RouteBuilder routeBuilder) {
		routeBuilder.onException(CustomRetryException.class)
		.log(LoggingLevel.INFO, "Entered into exception handler")
		.process(ex->{
			System.out.println(ex.getIn().getHeaders());
		})
		.to("activemq:queue:dead-letter");
	}

}
