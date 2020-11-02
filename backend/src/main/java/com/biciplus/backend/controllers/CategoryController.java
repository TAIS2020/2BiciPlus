package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.model.Category;
import com.biciplus.backend.repositories.CategoryRepository;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	CategoryRepository repository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Category post(@RequestBody Category entity) {
		entity.setId(null);
		return repository.save(entity);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Category get(Long id) {
		return repository.findById(id).get();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Category put(@RequestBody Category entity) {
		return repository.save(entity);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
