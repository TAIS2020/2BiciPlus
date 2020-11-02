package com.biciplus.backend.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class ComposedProduct extends Product {
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "product_sub_products", 
	    joinColumns = @JoinColumn(name="composed_product_id"),
	    inverseJoinColumns = @JoinColumn(name="sub_products_id"))
	private Set<SimpleProduct> subProducts;

	public Set<SimpleProduct> getSubProducts() {
		return subProducts;
	}

	public void setSubProducts(Set<SimpleProduct> subProducts) {
		this.subProducts = subProducts;
	}

	@Override
	public Long getPrice() {
		if(this.subProducts == null || this.subProducts.isEmpty()) return new Long(0);		
		return this.subProducts.stream().mapToLong(x -> x.getPrice()).sum();
	}

	@Override
	public String getType() {
		return "composed";
	}	
}
