package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.model.Product;
import com.biciplus.backend.repositories.ProductRepository;

@RestController
@RequestMapping("product")
public class ProductController<T extends Product> {
	
	@Autowired
	ProductRepository<T> repository;
	
	@RequestMapping(method = RequestMethod.POST)
	public T post(@RequestBody T entity) {
		entity.setId(null);
		return repository.save(entity);
	}

	@RequestMapping(method = RequestMethod.GET)
	public T get(Long id) {
		return repository.findById(id).get();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public T put(@RequestBody T entity) {
		return repository.save(entity);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
