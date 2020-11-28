package com.biciplus.backend.aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biciplus.backend.components.SplConfigurationComponent;
import com.biciplus.backend.exceptions.FeatureDisabledException;
import com.biciplus.backend.model.Product;

@Component
public class ComposedProductVariabilityHelper {

	@Autowired SplConfigurationComponent configuration;

	protected void checkIfComposedProductIsEnabled(Product product) throws FeatureDisabledException {
		if(!configuration.featureIsActive("ComposedECommerce") && product.getType().equals("composed")) throw new FeatureDisabledException("Composed products are not enabled");
	}
}
