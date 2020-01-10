package com.github.marceloleite2604.isstracker.inquisitor.model.google.marker;

import java.awt.Color;
import java.net.URI;

public class MarkerStyles {

	private MarkerSize size;

	private Color color;

	private Character label;

	private URI iconUri;

	private MarkerStyles(Builder builder) {
		this.size = builder.size;
		this.color = builder.color;
		this.label = builder.label;
		this.iconUri = builder.iconUri;
	}

	public MarkerSize getSize() {
		return size;
	}

	public Color getColor() {
		return color;
	}

	public Character getLabel() {
		return label;
	}

	public URI getIconUri() {
		return iconUri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((iconUri == null) ? 0 : iconUri.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarkerStyles other = (MarkerStyles) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color)) {
			return false;
		}
		if (iconUri == null) {
			if (other.iconUri != null)
				return false;
		} else if (!iconUri.equals(other.iconUri)) {
			return false;
		}
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label)) {
			return false;
		}
		return size != other.size;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private MarkerSize size;
		private Color color;
		private Character label;
		private URI iconUri;

		private Builder() {
		}

		public Builder size(MarkerSize size) {
			this.size = size;
			return this;
		}

		public Builder color(Color color) {
			this.color = color;
			return this;
		}

		public Builder label(Character label) {
			this.label = label;
			return this;
		}

		public Builder iconUri(URI iconUri) {
			this.iconUri = iconUri;
			return this;
		}

		public MarkerStyles build() {
			return new MarkerStyles(this);
		}
	}
}
