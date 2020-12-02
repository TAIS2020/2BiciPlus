package com.biciplus.backend.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biciplus.backend.components.SplConfigurationComponent;
import com.biciplus.backend.model.ComposedReport;
import com.biciplus.backend.model.Report;
import com.biciplus.backend.model.SimpleReport;

@Component
public class ReportFactory {

	@Autowired SplConfigurationComponent configuration;
	
	public Report getReport() {
		Report report = null;
		
		if (configuration.featureIsActive("SimpleReport")) {
			report = createSimpleReport();
		} else if (configuration.featureIsActive("ComposedReport")) {
			report = createComposedReport();
		}
		
		return report;
	}
	
	private SimpleReport createSimpleReport() {
		SimpleReport simpleReport = new SimpleReport();
		
		// add query.
		
		return simpleReport;
	}
	
	private ComposedReport createComposedReport() {
		ComposedReport simpleReport = new ComposedReport();

		// add query.
		
		return simpleReport;
	}
}
