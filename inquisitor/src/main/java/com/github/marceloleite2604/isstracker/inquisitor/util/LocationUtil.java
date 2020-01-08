package com.github.marceloleite2604.isstracker.inquisitor.util;

import com.github.marceloleite2604.isstracker.inquisitor.model.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class LocationUtil {

	public Coordinates asRadians(Coordinates location) {
		double latitude = Math.toRadians(location.getLatitude());
		double longitude = Math.toRadians(location.getLongitude());
		return Coordinates.builder()
				.latitude(latitude)
				.longitude(longitude)
				.build();
	}
}
