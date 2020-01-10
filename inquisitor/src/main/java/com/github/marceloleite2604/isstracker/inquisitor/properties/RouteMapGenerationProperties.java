package com.github.marceloleite2604.isstracker.inquisitor.properties;

import javax.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.ROUTE_MAP_GENERATION)
@Validated
public class RouteMapGenerationProperties {

	/**
	 * Time between each route map update.
	 */
	@Min(1)
	private int updateTimeMinutes;
	
	/**
	 * Minimal amount of positions to request a route map.
	 */
	@Min(1)
	private int minimalPositions;

	public int getUpdateTimeMinutes() {
		return updateTimeMinutes;
	}

	public void setUpdateTimeMinutes(int updateTimeMinutes) {
		this.updateTimeMinutes = updateTimeMinutes;
	}

	public int getMinimalPositions() {
		return minimalPositions;
	}

	public void setMinimalPositions(int minimalPositions) {
		this.minimalPositions = minimalPositions;
	}
}
