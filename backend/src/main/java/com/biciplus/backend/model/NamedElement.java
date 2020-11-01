package com.biciplus.backend.model;

public abstract class NamedElement extends IdentifiableElement {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
