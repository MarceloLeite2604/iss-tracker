package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes;

public class Pass {

	private long risetime;
	
	private long duration;

	public long getRisetime() {
		return risetime;
	}

	public long getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return "Pass [risetime=" + risetime + ", duration=" + duration + "]";
	}
}
