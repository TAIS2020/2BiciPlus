package com.biciplus.backend.factory;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biciplus.backend.components.SplConfigurationComponent;
import com.biciplus.backend.model.ComposedReport;
import com.biciplus.backend.model.Report;
import com.biciplus.backend.model.SimpleReport;

@Component
public class ReportFactory {

	@Autowired SplConfigurationComponent configuration;
	@PersistenceContext	EntityManager entityManager;
	
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
		
		Query q = entityManager.createNativeQuery( "SELECT id, name FROM product limit 1");
		
		@SuppressWarnings("unchecked")
		List<Object[]> array = (List<Object[]>) q.getResultList();
		
		simpleReport.setTest(String.valueOf(((BigInteger)array.get(0)[0]).longValue()));
		simpleReport.setTest2(String.valueOf(array.get(0)[1]));
		
		return simpleReport;
	}
	
	private ComposedReport createComposedReport() {
		ComposedReport simpleReport = new ComposedReport();

		// add query.
		
		return simpleReport;
	}
}
