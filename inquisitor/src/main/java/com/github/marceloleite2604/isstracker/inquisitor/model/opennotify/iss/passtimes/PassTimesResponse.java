package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes;

import java.util.List;

public class PassTimesResponse {

	private String message;
	
	private PassTimesRequest request;
	
	private List<Pass> response;

	public String getMessage() {
		return message;
	}

	public PassTimesRequest getRequest() {
		return request;
	}

	public List<Pass> getResponse() {
		return response;
	}
}
