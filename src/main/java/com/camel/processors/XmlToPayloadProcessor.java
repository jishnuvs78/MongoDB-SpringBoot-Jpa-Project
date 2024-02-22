package com.camel.processors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.camel.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XmlToPayloadProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
	
//		try {
//			File file=new File("input_box/data.xml");
//			JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
//			
//			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
//			Message msg = (Message)jaxbUnmarshaller.unmarshal(file);
//			
//			System.out.println(msg);
//			
//			ObjectMapper obj=new ObjectMapper();
//			
//			try {
//				String payloadStr=obj.writeValueAsString(msg);
//				System.out.println(payloadStr);
//				exchange.setProperty("payload",payloadStr);
//			}catch(IOException e){
//				e.printStackTrace();
//			}
//		}catch(JAXBException e) {
//			e.printStackTrace();
//		}
		
		try {
			File file=new File("input_box/data.xml");
			String payloadStr=new String(Files.readAllBytes(file.toPath()));
			System.out.println(payloadStr.getClass());
			exchange.setProperty("payload",payloadStr);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
