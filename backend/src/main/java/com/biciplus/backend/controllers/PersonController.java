package com.biciplus.backend.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.model.Customer;
import com.biciplus.backend.model.OrderHistory;
import com.biciplus.backend.model.Person;
import com.biciplus.backend.repositories.OrderHistoryRepository;
import com.biciplus.backend.repositories.PersonRepository;

@RestController
@RequestMapping("person")
public class PersonController<T extends Person>  {
	
	@Autowired
	PersonRepository<T> personRepository;
	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Response personPost(@RequestBody T entity) {
		entity.setId(null);
		return new Response(Response.Status.OK, personRepository.save(entity));
	}

	@RequestMapping(method = RequestMethod.GET)
	public Response list() {
		return new Response(Response.Status.OK, personRepository.findAll());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Response personGet(@PathVariable("id") Long id) {
		if(id == null) {
			return new Response(Response.Status.OK, personRepository.findAll());
		} else {
			return new Response(Response.Status.OK, personRepository.findById(id).get());			
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Response personPut(@RequestBody T entity) {
		return new Response(Response.Status.OK, personRepository.save(entity));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public Response personDelete(@PathVariable("id") Long id) {
		personRepository.deleteById(id);
		return new Response(Response.Status.OK);
	}	

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/{id}/orderHistory/order", method = RequestMethod.POST)
	public Response post(@RequestBody OrderHistory entity, @PathVariable("id") Long personId) {
		entity.setId(null);
		Customer customer = (Customer) personRepository.findById(personId).get();
		Set<OrderHistory> orderHistory = customer.getOrderHistory();
		OrderHistory persistedOrderHistory = orderHistoryRepository.save(entity);
		orderHistory.add(persistedOrderHistory);
		customer.setOrderHistory(orderHistory);
		personRepository.save((T) customer);
		return new Response(Response.Status.OK, orderHistory);
	}

	@RequestMapping(path = "/{id}/orderHistory", method = RequestMethod.GET)
	public Response get(@PathVariable("id") Long personId) {
		Customer customer = (Customer) personRepository.findById(personId).get();
		return new Response(Response.Status.OK, customer.getOrderHistory());
	}
}
