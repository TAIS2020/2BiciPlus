package com.biciplus.backend.model.config;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "configuration")
public class Configuration {
	List<Feature> features;

	public List<Feature> getFeatures() {
		return features;
	}

	@XmlElement(name = "feature")
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}	
}
