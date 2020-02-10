package com.github.marceloleite2604.isstracker.commons.model.db;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "route_maps")
public class RouteMap {

	@Id
	private LocalDateTime instant;

	@Column
	private byte[] data;
	
	public RouteMap() {
		super();
	}

	private RouteMap(Builder builder) {
		this.instant = builder.instant;
		this.data = builder.data;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public byte[] getData() {
		return data;
	}

	@Override
	public String toString() {
		return "RouteMap [instant=" + instant + ", data(size)=" + (data == null ? 0 : data.length) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instant == null) ? 0 : instant.hashCode());
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
		RouteMap other = (RouteMap) obj;
		if (instant == null) {
			if (other.instant != null)
				return false;
		} else if (!instant.equals(other.instant)) {
			return false;
		}
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private LocalDateTime instant;
		private byte[] data;

		private Builder() {
		}

		public Builder instant(LocalDateTime instant) {
			this.instant = instant;
			return this;
		}

		public Builder data(byte[] data) {
			this.data = data;
			return this;
		}

		public RouteMap build() {
			return new RouteMap(this);
		}
	}

}
