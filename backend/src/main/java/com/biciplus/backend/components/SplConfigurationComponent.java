package com.biciplus.backend.components;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.biciplus.backend.model.config.Configuration;

@Component
public class SplConfigurationComponent {
	private static String CONFIG_FILE_PATH = "C:\\Eclipse\\Workspaces\\eclipse-jee-2020-06-R\\2BiciPlus\\configs\\default.xml";
	private static Configuration configuration = null;	
	
	public Configuration getConfiguration() {
		//if(configuration != null) return configuration;
		
		try {			
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			configuration = (Configuration) unmarshaller.unmarshal(new File(CONFIG_FILE_PATH));
			return configuration;
		} catch (JAXBException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean featureIsActive(String featureName) {
		if(featureName == null || featureName.isEmpty()) return false;				
		return getConfiguration().getFeatures().stream().anyMatch(x -> x.getName().equals(featureName) && x.isSelected());
	}
}
