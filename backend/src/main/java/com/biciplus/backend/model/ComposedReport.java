package com.biciplus.backend.model;

public class ComposedReport extends Report {
	private String id;
	private String name;
	private String price;
	private String details;
	private String cname;

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

	public String getPrice() {
		return price;
	}	
	
	public void setPrice(String price) {
		this.price = price;
		
	}
	
	public String getDetails() {
		return details;
	}	
	
	public void setDetails(String details) {
		this.details = details;
		
	}
	
	public String getCname() {
		return cname;
	}	
	
	public void setCname(String cname) {
		this.cname = cname;
		
	}
	
}
