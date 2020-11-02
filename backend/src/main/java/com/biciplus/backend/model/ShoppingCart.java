package com.biciplus.backend.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ShoppingCart {
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "shoping_cart_products", 
	    joinColumns = @JoinColumn(name="shopping_cart_id"),
	    inverseJoinColumns = @JoinColumn(name="products_id"))
	private Set<Product> products;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Long getTotalPrice() {
		if(this.products == null || this.products.isEmpty()) return new Long(0);		
		return this.products.stream().mapToLong(x -> x.getPrice()).sum();
	}	
}
