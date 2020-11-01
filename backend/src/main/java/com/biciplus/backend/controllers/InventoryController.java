package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.model.Inventory;
import com.biciplus.backend.repositories.InventoryRepository;

@RestController()
public class InventoryController {

	@Autowired
	InventoryRepository repository;
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Inventory post(@RequestBody Inventory inventory) {
		return repository.save(new Inventory());
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public Inventory get(Long id) {
		return repository.findById(id).get();
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public Inventory put(@RequestBody Inventory inventory) {
		return repository.save(inventory);
	}

	@RequestMapping(path = "/", method = RequestMethod.DELETE)
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
