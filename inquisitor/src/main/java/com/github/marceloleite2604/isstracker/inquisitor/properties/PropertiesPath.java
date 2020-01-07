package com.github.marceloleite2604.isstracker.inquisitor.properties;

public final class PropertiesPath {
	
	private static final String BASE = "inquisitor";
	
	static final String PROGRAM = BASE;
	
	static final String DATABASE = BASE + ".database";
	
	static final String ENCRYPTION = BASE + ".encryption";

	private PropertiesPath() {
		// Private constructor to avoid object instantiation.
	}
}
