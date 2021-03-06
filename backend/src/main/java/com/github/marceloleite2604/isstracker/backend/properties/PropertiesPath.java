package com.github.marceloleite2604.isstracker.backend.properties;

public final class PropertiesPath {
	
	private static final String BASE = "backend";
	
	static final String PROGRAM = BASE;
	
	static final String DATABASE = BASE + ".database";
	
	static final String ENCRYPTION = BASE + ".encryption";

	static final String GIT = BASE + ".git";

	private PropertiesPath() {
		// Private constructor to avoid object instantiation.
	}
}
