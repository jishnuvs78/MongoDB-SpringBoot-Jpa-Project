package com.camel.routes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.config.CustomRetryException;
import com.camel.config.OnExceptionHandler;
import com.camel.processors.CheckRespProcessor;
import com.camel.processors.HeadersProcessor;
import com.camel.processors.MockRespProcessor;
import com.camel.processors.MsgToQueueProcessor;
import com.camel.processors.QueueProcessor;
import com.camel.processors.RetryProcessor;
import com.camel.processors.SanctionsProcessor;
import com.camel.processors.SetPayloadProcessor;
import com.camel.processors.StgIdProcessor;
import com.camel.processors.TransformPayloadProcessor;
import com.camel.processors.XmlToPayloadProcessor;
import com.camel.service.BusinessService;
import com.camel.service.ExchangeService;
import com.camel.service.MyDocumentService;
import com.camel.service.SummaryService;

@Component
public class XmlToPayloadRoute extends RouteBuilder{
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private SummaryService summaryService;
	
	@Autowired
	private SetPayloadProcessor setPayloadProcessor;
	
	@Autowired
	private TransformPayloadProcessor transformPayloadProcessor;
	
	@Autowired
	private StgIdProcessor stgIdProcessor;
	
	@Autowired
	private SanctionsProcessor sanctionsProcessor;
	
	@Autowired
	private MockRespProcessor mockRespProcessor;
	
	
	@Override
	public void configure() throws Exception {
					
		OnExceptionHandler.handle(this);
//		super.configure();
//		test();
		multiQueueRoute();
		mockRespRoute();
		sanctionsRoute();
//		retryRoute();
		sendToDest1();
//		transformPayload();
	}
		
	public void transformPayload() {
		
		rest("/mufg")
        .post("/postTransform").to("direct:transformPayload");
		
		from("direct:transformPayload")
		.process(transformPayloadProcessor);
		
	}
		
	public void multiQueueRoute() {
		
		from("activemq:queue:source")
		.to("direct:route1");
//		.to("direct:route2");
		
		from("activemq:queue:source2")
		.to("direct:route2");
	}
	
	public void sendToDest1() {
		
		from("direct:route1")
		.process(new QueueProcessor())
		.process(setPayloadProcessor)
		.process(transformPayloadProcessor)
		.setHeader("CamelHttpMethod", constant("POST"))
        .setHeader("Content-Type", constant("application/json"))
        .process(ex->{
        	String body=ex.getProperty("payload",String.class);
        	ex.getIn().setBody(body);
        })
        .to("https://cc4a3291-95c8-4ca4-a9d8-a1cc1b071570.mock.pstmn.io/mufg/jsonServer")
        .process(ex->{
        	System.out.println(ex.getIn().getBody(String.class));
        	ex.setProperty("stgId", "3");
        	ex.setProperty("status", "Resp from Json Server Recieved");
        })
        .process(stgIdProcessor);
//		.doTry()
//			.to("activemq:queue:dest1")
//			.process(exchange->{
//				throw new Exception("General Exception");
//			})
//		.doCatch(Exception.class)
//			.process(new HeadersProcessor())
//		.end();
		
	}
	
	public void sanctionsRoute() {
		from("direct:route2")
		.to("activemq:queue:xml_queue")
		.process(exchange->{
			LinkedHashMap<String, Object> headers = new LinkedHashMap<>(exchange.getIn().getHeaders());
			String Id=(String) headers.get("JMSCorrelationID");
			System.out.println(Id);
			String body=exchange.getIn().getBody(String.class);
			exchange.setProperty("payload",body);
			exchange.setProperty("summaryId", Id);
			exchange.setProperty("stgId", "4");
			exchange.setProperty("status", "Req sent to sanctions");
		})
		.process(stgIdProcessor)
		.to("activemq:queue:valid")
		.process(exchange->{
			exchange.setProperty("stgId", "5");
			exchange.setProperty("status", "Sanctions processed");
		})
		.process(stgIdProcessor)
		.process(sanctionsProcessor);
	}
	
	public void checkRespRoute() {
		
		from("activemq:queue:check_resp")
		.process(new CheckRespProcessor(summaryService))
//		.choice()
//			.when()
		.to("activemq:queue:non-violation");
		
	}
	
	public void retryRoute() {
		from("activemq:queue:dead-letter")
		.to("activemq:queue:source")
		.process(new RetryProcessor());
	}
	
	public void mockRespRoute() {
		from("activemq:queue:mock-queue")
		.process(mockRespProcessor);
	}
	
	
}
