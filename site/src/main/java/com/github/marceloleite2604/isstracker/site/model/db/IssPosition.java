package com.github.marceloleite2604.isstracker.site.model.db;

import com.github.marceloleite2604.isstracker.site.model.Coordinates;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "iss_position")
public class IssPosition {

	@Id
	private LocalDateTime instant;

	@Embedded
	private Coordinates coordinates;

	@Column(nullable = true)
	private Double speed;

	public IssPosition() {
		super();
	}

	private IssPosition(Builder builder) {
		this.instant = builder.instant;
		this.coordinates = builder.coordinates;
		this.speed = builder.speed;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public void setInstant(LocalDateTime instant) {
		this.instant = instant;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
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
		IssPosition other = (IssPosition) obj;
		if (instant == null) {
			if (other.instant != null)
				return false;
		} else if (!instant.equals(other.instant)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "IssPosition [instant=" + instant + ", coordinates=" + coordinates + ", speed=" + speed
				+ "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private LocalDateTime instant;
		private Coordinates coordinates;
		private Double speed;

		private Builder() {
		}

		public Builder instant(LocalDateTime instant) {
			this.instant = instant;
			return this;
		}

		public Builder location(Coordinates coordinates) {
			this.coordinates = coordinates;
			return this;
		}

		public Builder speed(Double speed) {
			this.speed = speed;
			return this;
		}

		public IssPosition build() {
			return new IssPosition(this);
		}
	}
}
