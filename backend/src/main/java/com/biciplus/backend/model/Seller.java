package com.biciplus.backend.model;

import javax.persistence.Entity;

@Entity
public class Seller extends Person {

	@Override
	public String getType() {
		return "seller";
	}	
}
