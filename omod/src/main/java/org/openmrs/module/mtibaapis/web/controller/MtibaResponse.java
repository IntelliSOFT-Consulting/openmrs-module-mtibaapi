package org.openmrs.module.mtibaapis.web.controller;

public class MtibaResponse {
	
	String status;
	
	Object response;
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Object getResponse() {
		return this.response;
	}
	
	public void setResponse(Object response) {
		this.response = response;
	}
	
}
