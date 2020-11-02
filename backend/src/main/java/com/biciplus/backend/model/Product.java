package com.biciplus.backend.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
	@JsonSubTypes.Type(value = SimpleProduct.class, name = "simple"),
	@JsonSubTypes.Type(value = ComposedProduct.class, name = "composed") 
	})
public abstract class Product {
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "product_categories", 
	    joinColumns = @JoinColumn(name="product_id"),
	    inverseJoinColumns = @JoinColumn(name="categories_id"))
	protected Set<Category> categories;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public abstract Long getPrice();
	public abstract String getType();
}
