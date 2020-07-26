package com.github.marceloleite2604.isstracker.backend.model.mapper;

import com.github.marceloleite2604.isstracker.backend.model.dto.IssInformationDto;
import com.github.marceloleite2604.isstracker.commons.model.IssPosition;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ListIssPositionToIssInformationDtoMapper {

  public IssInformationDto map(List<IssPosition> issPositions) {
    Double averageSpeed = calculateAverageSpeed(issPositions);

    List<IssPosition> copiedIssPositions = issPositions.stream()
        .map(IssPosition::new)
        .collect(Collectors.toUnmodifiableList());

    return IssInformationDto.builder()
        .issPositions(copiedIssPositions)
        .averageSpeed(averageSpeed)
        .build();
  }

  private Double calculateAverageSpeed(List<IssPosition> issPositions) {
    OptionalDouble optionalAverageSpeed = issPositions.stream()
        .map(IssPosition::getSpeed)
        .filter(Objects::nonNull)
        .mapToDouble(speed -> speed)
        .average();

    if (optionalAverageSpeed.isEmpty()) {
      return null;
    }
    return optionalAverageSpeed.getAsDouble();
  }
}
