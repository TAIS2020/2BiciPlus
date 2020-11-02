package com.biciplus.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Person {
    @OneToMany(cascade = CascadeType.REFRESH)
	protected Set<OrderHistory> orderHistory;

	public Set<OrderHistory> getOrderHistory() {
		if(this.orderHistory == null) return new HashSet<>();
		return orderHistory;
	}

	public void setOrderHistory(Set<OrderHistory> orderHistory) {
		this.orderHistory = orderHistory;
	}

	@Override
	public String getType() {
		return "customer";
	}	
}
