package com.github.marceloleite2604.isstracker.inquisitor.model.mapper;

import com.figtreelake.util.time.zoned.ZonedDateTimeUtil;
import com.github.marceloleite2604.isstracker.commons.model.db.IssPosition;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.locationnow.LocationNowResponse;
import com.github.marceloleite2604.isstracker.inquisitor.util.UnitsUtil;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class LocationNowResponseToIssPositionMapper {

	@Inject
	private UnitsUtil unitsBO;

	private static final double ISS_FLIGHT_ALTITUDE_METERS = 408000.0;

	@Inject
	private ZonedDateTimeUtil zonedDateTimeUtil;

	public IssPosition map(LocationNowResponse locationNowResponse) {
		return map(locationNowResponse, Optional.empty());
	}

	public IssPosition map(LocationNowResponse locationNowResponse,
			Optional<LocationNowResponse> optionalPreviousLocationNowResponse) {
		LocalDateTime instant = retrieveUtcDateTime(locationNowResponse.getTimestamp());

		Double speed = null;
		if (optionalPreviousLocationNowResponse.isPresent()) {
			speed = calculateSpeed(locationNowResponse, optionalPreviousLocationNowResponse.get());
		}

		return IssPosition.builder()
				.instant(instant)
				.location(locationNowResponse.getIssPosition())
				.speed(speed)
				.build();
	}

	private LocalDateTime retrieveUtcDateTime(long epochTime) {
		ZonedDateTime zonedDateTime = zonedDateTimeUtil.convertFromEpochTimeToUtcOffset(epochTime);
		return zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"))
				.toLocalDateTime();
	}

	private double calculateSpeed(LocationNowResponse locationNowResponse,
			LocationNowResponse previousLocationNowResponse) {

		long previousTimestamp = previousLocationNowResponse.getTimestamp();
		long currentTimestamp = locationNowResponse.getTimestamp();
		Duration duration = Duration.ofSeconds(currentTimestamp - previousTimestamp);

		return unitsBO.calculateSpeedKmh(previousLocationNowResponse.getIssPosition(),
				locationNowResponse.getIssPosition(), ISS_FLIGHT_ALTITUDE_METERS, duration);

	}
}
