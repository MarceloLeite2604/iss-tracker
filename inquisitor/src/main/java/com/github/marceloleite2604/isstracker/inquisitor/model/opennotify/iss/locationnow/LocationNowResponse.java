package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.locationnow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.Location;

public class LocationNowResponse {

	private String message;
	
	private long timestamp;
	
	@JsonProperty("iss_position")
	private Location issPosition;

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Location getIssPosition() {
		return issPosition;
	}

	@Override
	public String toString() {
		return "LocationNowResponse [message=" + message + ", timestamp=" + timestamp + ", issPosition="
				+ issPosition + "]";
	}
}
