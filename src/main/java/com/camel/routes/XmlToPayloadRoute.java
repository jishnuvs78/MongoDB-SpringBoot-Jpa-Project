package com.camel.routes;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.processors.SetPayloadProcessor;
import com.camel.processors.TransformPayloadProcessor;
import com.camel.processors.XmlToPayloadProcessor;
import com.camel.service.ExchangeService;
import com.camel.service.MyDocumentService;
import com.camel.service.SummaryService;

@Component
public class XmlToPayloadRoute extends RouteBuilder{

//	private final MyDocumentService documentService;
//	
//	@Autowired
//    public XmlToPayloadRoute(MyDocumentService documentService) {
//        this.documentService = documentService;
//    }
	
	private final SummaryService summaryService;
	private final ExchangeService exchangeService;
	
	@Autowired
	public XmlToPayloadRoute(SummaryService summaryService,ExchangeService exchangeService) {
		this.summaryService=summaryService;
		this.exchangeService=exchangeService;
	}
	
	@Override
	public void configure() throws Exception {
		
		XmlToPayload();
		transformPayload();
	}
	
	public void XmlToPayload() {
		
		from("file:input_box?noop=true")
		.process(new XmlToPayloadProcessor())
		.process(new SetPayloadProcessor(summaryService,exchangeService));
		
	}
	
	public void transformPayload() {
		
		rest("/mufg")
        .post("/postTransform").to("direct:transformPayload");
		
		from("direct:transformPayload")
		.process(new TransformPayloadProcessor(exchangeService));
		
	}

}
