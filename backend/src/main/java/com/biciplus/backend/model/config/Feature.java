package com.biciplus.backend.model.config;

import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Feature {
	private String automatic;
	private String manual;
	private String name;
	private static String SELECTED_PARAM = "selected";

	@JsonIgnore
	public String getAutomatic() {
		return automatic;
	}
	@XmlAttribute(name = "automatic")
	public void setAutomatic(String automatic) {
		this.automatic = automatic;
	}
	@JsonIgnore
	public String getManual() {
		return manual;
	}
	@XmlAttribute(name = "manual")
	public void setManual(String manual) {
		this.manual = manual;
	}
	public String getName() {
		return name;
	}
	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSelected() {
		return (automatic != null && automatic.equals(SELECTED_PARAM) || manual != null && manual.equals(SELECTED_PARAM));
	}
}
