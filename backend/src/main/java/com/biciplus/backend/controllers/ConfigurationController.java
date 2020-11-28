package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.CustomExceptionHandler;
import com.biciplus.backend.components.SplConfigurationComponent;
import com.biciplus.backend.controllers.util.Response;

@RestController
@RequestMapping("api/configuration")
public class ConfigurationController extends CustomExceptionHandler {

	@Autowired
	SplConfigurationComponent configurationComponent;
	
	@RequestMapping(method = RequestMethod.GET)
	public Response get() {
		return new Response(Response.Status.OK, configurationComponent.getConfiguration());
	}
}
