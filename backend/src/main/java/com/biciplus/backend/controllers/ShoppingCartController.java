package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.model.ShoppingCart;
import com.biciplus.backend.repositories.ShoppingCartRepository;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

	@Autowired
	ShoppingCartRepository repository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Response post(@RequestBody ShoppingCart entity) {
		entity.setId(null);
		return new Response(Response.Status.OK, repository.save(entity));
	}

	@RequestMapping(method = RequestMethod.GET)
	public Response list() {
		return new Response(Response.Status.OK, repository.findAll());
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Response get(@PathVariable("id") Long id) {
		return new Response(Response.Status.OK, repository.findById(id).get());			
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Response put(@RequestBody ShoppingCart entity) {
		return new Response(Response.Status.OK, repository.save(entity));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return new Response(Response.Status.OK);
	}
}
