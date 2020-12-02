package com.biciplus.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.CustomExceptionHandler;
import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.exceptions.FeatureDisabledException;
import com.biciplus.backend.factory.ReportFactory;
import com.biciplus.backend.model.Report;

@RestController
@RequestMapping("api/report")
public class ReportController<T extends Report> extends CustomExceptionHandler {
	@Autowired ReportFactory reportFactory;
	
	@RequestMapping(method = RequestMethod.GET)
	public Response get() throws FeatureDisabledException {
		Report report =  reportFactory.getReport();
		if(report == null) throw new FeatureDisabledException("Reports are not enabled"); 
		return new Response(Response.Status.OK, report);
	}
}
