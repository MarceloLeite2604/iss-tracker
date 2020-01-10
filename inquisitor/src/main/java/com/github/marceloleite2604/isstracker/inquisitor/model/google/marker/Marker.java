package com.github.marceloleite2604.isstracker.inquisitor.model.google.marker;

import com.github.marceloleite2604.isstracker.commons.model.Coordinates;
import java.util.Collections;
import java.util.List;

public class Marker {
	
	private MarkerStyles styles;
	
	private List<Coordinates> coordinates;

	private Marker(Builder builder) {
		this.styles = builder.styles;
		this.coordinates = builder.coordinates;
	}

	public MarkerStyles getStyles() {
		return styles;
	}

	public List<Coordinates> getCoordinates() {
		return coordinates;
	}

	@Override
	public String toString() {
		return "Marker [styles=" + styles + ", coordinates=" + coordinates + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private MarkerStyles styles;
		private List<Coordinates> coordinates = Collections.emptyList();

		private Builder() {
		}

		public Builder styles(MarkerStyles styles) {
			this.styles = styles;
			return this;
		}

		public Builder coordinates(List<Coordinates> coordinates) {
			this.coordinates = coordinates;
			return this;
		}

		public Marker build() {
			return new Marker(this);
		}
	}
}
