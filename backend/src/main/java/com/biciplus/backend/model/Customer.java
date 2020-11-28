package com.biciplus.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer extends Person {
    @OneToMany(cascade = CascadeType.REFRESH)
	protected Set<OrderHistory> orderHistory;
    @OneToOne
    private ShoppingCart shoppingCart;
    @OneToMany(cascade = CascadeType.REFRESH)
	protected Set<Question> questions;
    
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

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Set<Question> getQuestions() {
		if(this.questions == null) return new HashSet<>();
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
}
