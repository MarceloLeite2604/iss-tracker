package com.github.marceloleite2604.isstracker.commons.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {

	@Column(nullable = false)
	private double latitude;

	@Column(nullable = false)
	private double longitude;

	public Coordinates() {
		super();
	}

	private Coordinates(Builder builder) {
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
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
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

		public Coordinates build() {
			return new Coordinates(this);
		}
	}
}
