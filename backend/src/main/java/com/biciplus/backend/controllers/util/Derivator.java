package com.biciplus.backend.controllers.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.biciplus.backend.model.config.Configuration;

public class Derivator {
	private static String VARIABILITY_TREE_CONFIG_FILE_PATH = "C:\\Eclipse\\Workspaces\\eclipse-jee-2020-06-R\\2BiciPlus\\configs\\default.xml";
	private static String PROJECT_CONFIG_FILE_PATH = "src/main/resources/default.xml";
	private static Configuration configuration = null;	
	
	public static void main(String[] args) {
		Derivator derivator = new Derivator();
		derivator.getConfiguration();
		derivator.configureComposedProductsFeature(derivator.featureIsActive("ComposedECommerce"));
		derivator.configureCustomerSupportFeature(derivator.featureIsActive("MessageBasedCustomerSupport"));
	}
	
	public void configureComposedProductsFeature(boolean enable) {
		if(enable) {
			System.out.println("Enabling Composed Products Feature");
			replaceFileContent("src/main/java/com/biciplus/backend/aspects/ComposedProductVariabilityHelper.java", "[\\\\/]*@Component", "//@Component");
			replaceFileContent("src/main/java/com/biciplus/backend/aspects/InventoryVariabilityAspect.java", "[\\\\/]*@Component", "//@Component");
			replaceFileContent("src/main/java/com/biciplus/backend/aspects/ProductVariabilityAspect.java", "[\\\\/]*@Component", "//@Component");
		} else {
			System.out.println("Disabling Composed Products Feature");
			replaceFileContent("src/main/java/com/biciplus/backend/aspects/ComposedProductVariabilityHelper.java", "[\\/]*@Component", "@Component");
			replaceFileContent("src/main/java/com/biciplus/backend/aspects/InventoryVariabilityAspect.java", "[\\/]*@Component", "@Component");
			replaceFileContent("src/main/java/com/biciplus/backend/aspects/ProductVariabilityAspect.java", "[\\/]*@Component", "@Component");
		}
	}
	
	public void configureCustomerSupportFeature(boolean enable) {
		if(enable) {
			System.out.println("Enabling Customer Support Feature");
			replaceFileContent("src/main/java/com/biciplus/backend/controllers/QuestionController.java", "[\\/]*@RestController", "@RestController");
		} else {
			System.out.println("Disabling Customer Support Feature");
			replaceFileContent("src/main/java/com/biciplus/backend/controllers/QuestionController.java", "[\\\\/]*@RestController", "//@RestController");
		}
	}
	
	public static void replaceFileContent(String filePath, String regex, String replacingString) {
		Charset charset = StandardCharsets.UTF_8;

		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)), charset);
			content = content.replaceAll(regex, replacingString);
			Files.write(Paths.get(filePath), content.getBytes(charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Configuration getConfiguration() {
		if(configuration != null) return configuration;
		
		try {			
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			configuration = (Configuration) unmarshaller.unmarshal(new File(VARIABILITY_TREE_CONFIG_FILE_PATH));
			Files.copy(Paths.get(VARIABILITY_TREE_CONFIG_FILE_PATH), Paths.get(PROJECT_CONFIG_FILE_PATH), StandardCopyOption.REPLACE_EXISTING);
			return configuration;
		} catch (JAXBException | IOException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean featureIsActive(String featureName) {
		if(featureName == null || featureName.isEmpty()) return false;				
		return getConfiguration().getFeatures().stream().anyMatch(x -> x.getName().equals(featureName) && x.isSelected());
	}
}
