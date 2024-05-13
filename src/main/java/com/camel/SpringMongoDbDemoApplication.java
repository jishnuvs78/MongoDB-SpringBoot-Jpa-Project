package com.camel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAutoConfiguration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.lib.camel.camelRoutesLibrary"})
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
