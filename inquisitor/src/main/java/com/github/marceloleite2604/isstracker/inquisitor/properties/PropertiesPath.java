package com.github.marceloleite2604.isstracker.inquisitor.properties;

public final class PropertiesPath {
	
	private static final String BASE = "inquisitor";
	
	static final String PROGRAM = BASE;
	
	static final String DATABASE = BASE + ".database";
	
	static final String ENCRYPTION = BASE + ".encryption";
	
	static final String GOOGLE = BASE + ".google";
	
	private static final String ROUTE_MAP = BASE + ".route-map";
	
	static final String ROUTE_MAP_GENERATION = ROUTE_MAP + ".generation";
	
	static final String ROUTE_MAP_STYLE = ROUTE_MAP + ".style";

	private PropertiesPath() {
		// Private constructor to avoid object instantiation.
	}
}
