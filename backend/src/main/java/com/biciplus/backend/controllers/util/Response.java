package com.biciplus.backend.controllers.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Response {
	public enum Status {
		OK,
		ERROR,
		FEATURE_DISABLED
	}
	
	private String status;
	private Object result;

	public Response(Status status) {
		super();
		this.status = status.name();
		this.result = null;
	}
	
	public Response(Status status, Object resutlt) {
		super();
		this.status = status.name();
		this.result = resutlt;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonInclude(Include.NON_NULL)
	public Object getResult() {
		return result;
	}
	
	public void setResult(Object resutlt) {
		this.result = resutlt;
	}
}
