package com.biciplus.backend.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.CustomExceptionHandler;
import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.model.OrderHistory;
import com.biciplus.backend.repositories.OrderHistoryRepository;

@RestController
@RequestMapping("api/orderHistory")
public class OrderHistoryController extends CustomExceptionHandler {

	@Autowired
	OrderHistoryRepository repository;

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
	
	@RequestMapping(path = "/{id}/shipping", method = RequestMethod.POST)
	public Response post(@PathVariable("id") Long id) {
		OrderHistory entity = repository.findEntityById(id);
		if(entity.getShippingDate() != null)entity.setShippingDate(new Date());
		return new Response(Response.Status.OK, repository.save(entity));
	}
}
