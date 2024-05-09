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
	private String fraudFlag;
	private String sanctionsFlag;
	private String finalFlag;

	public Summary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Summary(String id, String messageType, String fraudStatus, String eventId, String fraudFlag,
			String sanctionsFlag, String finalFlag) {
		super();
		this.id = id;
		this.messageType = messageType;
		this.fraudStatus = fraudStatus;
		this.eventId = eventId;
		this.fraudFlag = fraudFlag;
		this.sanctionsFlag = sanctionsFlag;
		this.finalFlag = finalFlag;
	}


	public String getFinalFlag() {
		return finalFlag;
	}

	public void setFinalFlag(String finalFlag) {
		this.finalFlag = finalFlag;
	}

	public String getSanctionsFlag() {
		return sanctionsFlag;
	}

	public void setSanctionsFlag(String sanctionsFlag) {
		this.sanctionsFlag = sanctionsFlag;
	}

	public String getFraudFlag() {
		return fraudFlag;
	}

	public void setFraudFlag(String fraudFlag) {
		this.fraudFlag = fraudFlag;
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
				+ eventId + ", fraudFlag=" + fraudFlag + ", sanctionsFlag=" + sanctionsFlag + "]";
	}


}
