package com.github.marceloleite2604.isstracker.site.model.google.marker;

public enum MarkerSize {

	TINY("tiny"),
	MID("mid"),
	SMALL("small");

	private String value;

	private MarkerSize(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
