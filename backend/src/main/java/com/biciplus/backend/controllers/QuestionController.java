package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.repositories.QuestionRepository;

//@RestController
@RequestMapping("api/question")
public class QuestionController {

	@Autowired
	QuestionRepository repository;

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
}
