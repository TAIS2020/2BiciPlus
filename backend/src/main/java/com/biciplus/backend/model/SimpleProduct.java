package com.biciplus.backend.model;

import javax.persistence.Entity;

@Entity
public class SimpleProduct extends Product {

	protected String name;
	private Long price;
	private String details;
	private String photoURL;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public Long getPrice() {
		return price;
	} 

	@Override
	public String getType() {
		return "simple";
	}	
}
