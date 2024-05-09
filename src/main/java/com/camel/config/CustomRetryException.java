package com.camel.config;

@SuppressWarnings("serial")
public class CustomRetryException extends Exception {
		
	public CustomRetryException(String message) {
        super(message);
    }
	
}
