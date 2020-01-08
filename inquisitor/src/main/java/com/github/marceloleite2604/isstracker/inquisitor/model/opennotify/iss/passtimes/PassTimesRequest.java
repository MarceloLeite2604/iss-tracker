package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.marceloleite2604.isstracker.inquisitor.model.Coordinates;

public class PassTimesRequest {

	private int altitude;

	private long datetime;

	private int passes;

	@JsonUnwrapped
	private Coordinates location;

	private PassTimesRequest(Builder builder) {
		this.altitude = builder.altitude;
		this.datetime = builder.datetime;
		this.passes = builder.passes;
		this.location = builder.location;
	}

	public int getAltitude() {
		return altitude;
	}

	public long getDatetime() {
		return datetime;
	}

	public int getPasses() {
		return passes;
	}

	public Coordinates getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "PassTimesRequest [altitude=" + altitude + ", datetime=" + datetime + ", passes="
				+ passes + ", location=" + location + "]";
	}

	public abstract static class PathParameters {
		public static final String LATITUDE = "lat";
		public static final String LONGITUDE = "long";
		public static final String ALTITUDE = "alt";
		public static final String NUMBER = "n";

		private PathParameters() {
			// Private constructor to avoid object instantiation.
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int altitude;
		private long datetime;
		private int passes;
		private Coordinates location;

		private Builder() {
		}

		public Builder altitude(int altitude) {
			this.altitude = altitude;
			return this;
		}

		public Builder datetime(long datetime) {
			this.datetime = datetime;
			return this;
		}

		public Builder passes(int passes) {
			this.passes = passes;
			return this;
		}

		public Builder location(Coordinates location) {
			this.location = location;
			return this;
		}

		public PassTimesRequest build() {
			return new PassTimesRequest(this);
		}
	}
}
