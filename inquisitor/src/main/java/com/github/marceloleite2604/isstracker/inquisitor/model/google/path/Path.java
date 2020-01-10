package com.github.marceloleite2604.isstracker.inquisitor.model.google.path;

import com.github.marceloleite2604.isstracker.commons.model.Coordinates;
import java.util.Collections;
import java.util.List;

public class Path {

	private PathStyles styles;

	private List<Coordinates> coordinates;

	private Path(Builder builder) {
		this.styles = builder.styles;
		this.coordinates = builder.coordinates;
	}

	public PathStyles getStyles() {
		return styles;
	}

	public List<Coordinates> getCoordinates() {
		return coordinates;
	}

	@Override
	public String toString() {
		return "Path [styles=" + styles + ", coordinates=" + coordinates + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private PathStyles styles;
		private List<Coordinates> coordinates = Collections.emptyList();

		private Builder() {
		}

		public Builder styles(PathStyles styles) {
			this.styles = styles;
			return this;
		}

		public Builder coordinates(List<Coordinates> coordinates) {
			this.coordinates = coordinates;
			return this;
		}

		public Path build() {
			return new Path(this);
		}
	}
}
