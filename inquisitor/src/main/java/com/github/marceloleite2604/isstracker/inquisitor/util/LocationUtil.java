package com.github.marceloleite2604.isstracker.inquisitor.util;

import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationUtil {

	public Location asRadians(Location location) {
		double latitude = Math.toRadians(location.getLatitude());
		double longitude = Math.toRadians(location.getLongitude());
		return Location.builder()
				.latitude(latitude)
				.longitude(longitude)
				.build();
	}
}
