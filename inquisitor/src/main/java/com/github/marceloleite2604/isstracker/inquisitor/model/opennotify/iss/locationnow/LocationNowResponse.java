package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.locationnow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marceloleite2604.isstracker.commons.model.Coordinates;

public class LocationNowResponse {

	private String message;
	
	private long timestamp;
	
	@JsonProperty("iss_position")
	private Coordinates issPosition;

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Coordinates getIssPosition() {
		return issPosition;
	}

	@Override
	public String toString() {
		return "LocationNowResponse [message=" + message + ", timestamp=" + timestamp + ", issPosition="
				+ issPosition + "]";
	}
}
