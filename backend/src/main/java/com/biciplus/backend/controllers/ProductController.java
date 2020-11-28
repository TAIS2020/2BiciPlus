package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.CustomExceptionHandler;
import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.model.Product;
import com.biciplus.backend.repositories.ProductRepository;

@RestController
@RequestMapping("api/product")
public class ProductController<T extends Product> extends CustomExceptionHandler {
	
	@Autowired
	ProductRepository<T> repository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Response post(@RequestBody T entity) {
		entity.setId(null);
		return new Response(Response.Status.OK, repository.save(entity));
	}

	@RequestMapping(method = RequestMethod.GET)
	public Response list() {
		return new Response(Response.Status.OK, repository.findAll());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Response get(@PathVariable("id") Long id) {
		if(id == null) {
			return new Response(Response.Status.OK, repository.findAll());
		} else {
			return new Response(Response.Status.OK, repository.findEntityById(id));			
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Response put(@RequestBody T entity) {
		return new Response(Response.Status.OK, repository.save(entity));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return new Response(Response.Status.OK);
	}
}
