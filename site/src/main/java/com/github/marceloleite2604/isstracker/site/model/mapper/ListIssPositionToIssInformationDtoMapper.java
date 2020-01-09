package com.github.marceloleite2604.isstracker.site.model.mapper;

import com.github.marceloleite2604.isstracker.site.model.db.IssPosition;
import com.github.marceloleite2604.isstracker.site.model.dto.IssInformationDTO;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import org.springframework.stereotype.Component;

@Component
public class ListIssPositionToIssInformationDtoMapper {

	public IssInformationDTO map(List<IssPosition> issPositions) {
		Double averageSpeed = calculateAverageSpeed(issPositions);

		return IssInformationDTO.builder()
				.issPositions(issPositions)
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
