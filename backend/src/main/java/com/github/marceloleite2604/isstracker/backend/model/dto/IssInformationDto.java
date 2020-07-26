package com.github.marceloleite2604.isstracker.backend.model.dto;

import com.github.marceloleite2604.isstracker.commons.model.IssPosition;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collections;
import java.util.List;

@Schema(name = "iss-information", description = "Information about the space station")
public class IssInformationDto {

  private List<IssPosition> issPositions;

  @Schema(description = "The space station average speed.")
  private Double averageSpeed;

  private IssInformationDto(Builder builder) {
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
    return "IssInformationDto [issPositions=" + issPositions + ", averageSpeed=" + averageSpeed
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

    public IssInformationDto build() {
      return new IssInformationDto(this);
    }
  }
}
