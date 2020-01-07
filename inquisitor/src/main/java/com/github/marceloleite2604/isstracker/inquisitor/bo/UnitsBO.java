package com.github.marceloleite2604.isstracker.inquisitor.bo;

import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.Location;
import com.github.marceloleite2604.isstracker.inquisitor.util.LocationUtil;
import java.time.Duration;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class UnitsBO {
	
	private static final double EARTH_RADIUS_METERS = 6378100.0;
	
	private static final double SECONDS_IN_AN_HOUR = 3600.0;
	
	private static final double KILO = 1000.0;

	@Inject
	private LocationUtil locationUtil;

	public double calculateSpeedKmh(Location start, Location end, double altitude, Duration duration) {
		Location startInRadians = locationUtil.asRadians(start);
		Location endInRadians = locationUtil.asRadians(end);
		
		double radius = EARTH_RADIUS_METERS + altitude;
		
		double rho1 = radius * Math.cos(startInRadians.getLatitude());
		double z1 = radius * Math.sin(startInRadians.getLatitude());
		double x1 = rho1 * Math.cos(startInRadians.getLongitude());
		double y1 = rho1 * Math.sin(startInRadians.getLongitude());
		
		double rho2 = radius * Math.cos(endInRadians.getLatitude());
		double z2 = radius * Math.sin(endInRadians.getLatitude());
		double x2 = rho2 * Math.cos(endInRadians.getLongitude());
		double y2 = rho2 * Math.sin(endInRadians.getLongitude());
		
		double dot = (x1 * x2 + y1 * y2 + z1 * z2);
		double cosTheta = dot / Math.pow(radius, 2);
		
		double theta = Math.acos(cosTheta);
		
		double distanceKilometers = (radius * theta)/KILO;
		
		double timeHours = ((double)duration.getSeconds())/SECONDS_IN_AN_HOUR;
		
		return distanceKilometers/timeHours;
	}
}
