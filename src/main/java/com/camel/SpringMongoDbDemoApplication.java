package com.camel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.camel.model.MyDocument;
import com.camel.service.MyDocumentService;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableTransactionManagement
public class SpringMongoDbDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMongoDbDemoApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		MyDocument document = new MyDocument();
//        document.setName("Example Name");
//        String insertedDocumentId = documentService.insertDocument(document);
//        System.out.println("Inserted document ID: " + insertedDocumentId);
//		
//	}

}
