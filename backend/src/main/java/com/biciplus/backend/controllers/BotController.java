package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.CustomExceptionHandler;
import com.biciplus.backend.components.SplConfigurationComponent;
import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.exceptions.FeatureDisabledException;
import com.biciplus.backend.model.Question;

@RestController
@RequestMapping("api/bot/question")
public class BotController extends CustomExceptionHandler{

	@Autowired SplConfigurationComponent configuration;
	
	@RequestMapping(method = RequestMethod.POST)
	public Response post(@RequestBody Question question) throws FeatureDisabledException {
		if(!configuration.featureIsActive("BotCustomerSupport")) throw new FeatureDisabledException("Bot is not enabled");
		return new Response(Response.Status.OK, "Lorem ipsum dolor sit amet consectetur adipiscing elit quis tristique maecenas consequat cras suscipit, duis morbi gravida eros purus molestie sodales tortor eleifend ad in malesuada. Tempus dapibus proin cum natoque neque suspendisse metus, condimentum pharetra donec potenti odio tempor volutpat, nec fames elementum convallis justo congue. Eros leo platea venenatis at et curabitur vitae nulla, cras bibendum cum sodales eu scelerisque penatibus eget rhoncus, quam elementum accumsan lacus maecenas non ante.");
	}
}
