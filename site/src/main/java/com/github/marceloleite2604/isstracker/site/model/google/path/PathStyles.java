package com.github.marceloleite2604.isstracker.site.model.google.path;

import java.awt.Color;

public class PathStyles {

	private Integer weight;

	private Color color;

	private Color fillColor;

	private Boolean geodesic;

	private PathStyles(Builder builder) {
		this.weight = builder.weight;
		this.color = builder.color;
		this.fillColor = builder.fillColor;
		this.geodesic = builder.geodesic;
	}

	public Integer getWeight() {
		return weight;
	}

	public Color getColor() {
		return color;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Boolean getGeodesic() {
		return geodesic;
	}

	@Override
	public String toString() {
		return "PathStyles [weight=" + weight + ", color=" + color + ", fillColor=" + fillColor
				+ ", geodesic=" + geodesic + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Integer weight;
		private Color color;
		private Color fillColor;
		private Boolean geodesic;

		private Builder() {
		}

		public Builder weight(Integer weight) {
			this.weight = weight;
			return this;
		}

		public Builder color(Color color) {
			this.color = color;
			return this;
		}

		public Builder fillColor(Color fillColor) {
			this.fillColor = fillColor;
			return this;
		}

		public Builder geodesic(Boolean geodesic) {
			this.geodesic = geodesic;
			return this;
		}

		public PathStyles build() {
			return new PathStyles(this);
		}
	}
}
