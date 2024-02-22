package com.camel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "myCollection")
public class MyDocument {
	
	@Id
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public MyDocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyDocument(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


}
