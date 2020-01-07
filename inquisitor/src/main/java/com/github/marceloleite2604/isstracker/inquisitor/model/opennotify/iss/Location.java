package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss;

public class Location {

	private double latitude;

	private double longitude;

	public Location() {
		super();
	}

	private Location(Builder builder) {
		this.latitude = builder.latitude;
		this.longitude = builder.longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private double latitude;
		private double longitude;

		private Builder() {
		}

		public Builder latitude(double latitude) {
			this.latitude = latitude;
			return this;
		}

		public Builder longitude(double longitude) {
			this.longitude = longitude;
			return this;
		}

		public Location build() {
			return new Location(this);
		}
	}
}
