package com.camel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exchange")
public class Exchange {
	
	@Id
	private String Id;
	
	private String summaryId;
	private String stgId;
	private String desc;
	private String payload;
	private String status;
	
	
	public Exchange() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Exchange(String id, String summaryId, String stgId, String desc, String payload, String status) {
		super();
		Id = id;
		this.summaryId = summaryId;
		this.stgId = stgId;
		this.desc = desc;
		this.payload = payload;
		this.status = status;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getStgId() {
		return stgId;
	}

	public void setStgId(String stgId) {
		this.stgId = stgId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	
	
	

}
