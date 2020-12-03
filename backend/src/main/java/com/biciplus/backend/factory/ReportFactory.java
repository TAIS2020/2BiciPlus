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
		
		Query q = entityManager.createNativeQuery( "SELECT id, name, price FROM product where id="
				+ " (SELECT MAX(id) FROM product WHERE details is not null)");
		
		@SuppressWarnings("unchecked")
		List<Object[]> array = (List<Object[]>) q.getResultList();
		
		simpleReport.setId(String.valueOf(((BigInteger)array.get(0)[0]).longValue()));
		simpleReport.setName(String.valueOf(array.get(0)[1]));
		simpleReport.setPrice(String.valueOf(((BigInteger)array.get(0)[2]).longValue()));
		
		return simpleReport;
	}
	
	private ComposedReport createComposedReport() {
		ComposedReport composedReport = new ComposedReport();

		Query q = entityManager.createNativeQuery( "SELECT product.id, product.name, product.price, product.details, category.name as cname "
				+ "FROM product,product_categories,category"
				+ " WHERE product.id=product_categories.product_id"
				+ "	AND category.id=product_categories.categories_id"
				+ " AND product.id = (SELECT MAX(id) FROM product WHERE dtype <>'ComposedProduct')");
		
		@SuppressWarnings("unchecked")
		List<Object[]> array = (List<Object[]>) q.getResultList();
		
		composedReport.setId(String.valueOf(((BigInteger)array.get(0)[0]).longValue()));
		composedReport.setName(String.valueOf(array.get(0)[1]));
		composedReport.setPrice(String.valueOf(((BigInteger)array.get(0)[2]).longValue()));
		composedReport.setDetails(String.valueOf(array.get(0)[3]));
		composedReport.setCname(String.valueOf(array.get(0)[4]));
		
		return composedReport;
	}
}
