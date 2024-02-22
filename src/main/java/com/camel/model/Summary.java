package com.camel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "summary")
public class Summary {
	
	@Id
	private String id;
	
	private String messageType;
	private String fraudStatus;
	private String eventId;
	
	public Summary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Summary(String id, String messageType, String fraudStatus, String eventId) {
		super();
		this.id = id;
		this.messageType = messageType;
		this.fraudStatus = fraudStatus;
		this.eventId = eventId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getFraudStatus() {
		return fraudStatus;
	}

	public void setFraudStatus(String fraudStatus) {
		this.fraudStatus = fraudStatus;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "Summary [id=" + id + ", messageType=" + messageType + ", fraudStatus=" + fraudStatus + ", eventId="
				+ eventId + "]";
	}

}
