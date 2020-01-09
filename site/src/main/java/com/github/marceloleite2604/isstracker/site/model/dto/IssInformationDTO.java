package com.github.marceloleite2604.isstracker.site.model.dto;

import com.github.marceloleite2604.isstracker.site.model.db.IssPosition;
import java.util.Collections;
import java.util.List;

public class IssInformationDTO {

	private List<IssPosition> issPositions;
	
	private Double averageSpeed;

	private IssInformationDTO(Builder builder) {
		this.issPositions = builder.issPositions;
		this.averageSpeed = builder.averageSpeed;
	}

	public List<IssPosition> getIssPositions() {
		return issPositions;
	}

	public Double getAverageSpeed() {
		return averageSpeed;
	}

	@Override
	public String toString() {
		return "IssInformationDTO [issPositions=" + issPositions + ", averageSpeed=" + averageSpeed
				+ "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private List<IssPosition> issPositions = Collections.emptyList();
		private Double averageSpeed;

		private Builder() {
		}

		public Builder issPositions(List<IssPosition> issPositions) {
			this.issPositions = issPositions;
			return this;
		}

		public Builder averageSpeed(Double averageSpeed) {
			this.averageSpeed = averageSpeed;
			return this;
		}

		public IssInformationDTO build() {
			return new IssInformationDTO(this);
		}
	}
}
