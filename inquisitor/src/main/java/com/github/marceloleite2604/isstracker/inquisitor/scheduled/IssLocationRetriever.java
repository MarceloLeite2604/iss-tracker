package com.github.marceloleite2604.isstracker.inquisitor.scheduled;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.bo.IssApiBO;
import com.github.marceloleite2604.isstracker.inquisitor.bo.UnitsBO;
import com.github.marceloleite2604.isstracker.inquisitor.model.message.OutputMessage;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.Location;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.locationnow.LocationNowResponse;
import com.github.marceloleite2604.util.time.zoned.ZonedDateTimeUtil;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IssLocationRetriever {

	private static final long EXECUTION_PERIOD_MILLIS = 60000;

	private static final Logger LOGGER = LoggerFactory.getLogger(IssLocationRetriever.class);

	private static final double ISS_FLIGHT_ALTITUDE_METERS = 408000.0;

	@Inject
	private IssApiBO issApiBo;

	@Inject
	private UnitsBO unitsBo;

	@Inject
	private Blimp blimp;

	@Inject
	private ZonedDateTimeUtil zonedDateTimeUtil;

	@Inject
	private ZoneId zoneId;

	private Optional<LocationNowResponse> previousLocationResponse;

	public IssLocationRetriever() {
		previousLocationResponse = Optional.empty();
	}

	@Scheduled(fixedDelay = EXECUTION_PERIOD_MILLIS)
	public void retrieve() {
		Optional<LocationNowResponse> locationNowResponse = issApiBo.locationNow();
		locationNowResponse.ifPresent(this::logInformation);
		previousLocationResponse = locationNowResponse;
	}

	private void logInformation(LocationNowResponse locationNowResponse) {
		logLocation(locationNowResponse);
		logSpeed(locationNowResponse);
	}

	private void logLocation(LocationNowResponse locationNowResponse) {
		if (LOGGER.isInfoEnabled()) {
			ZonedDateTime zonedDateTime = zonedDateTimeUtil
					.convertFromEpochTime(locationNowResponse.getTimestamp());
			ZonedDateTime localZonedDateTime = zonedDateTime.withZoneSameInstant(zoneId);
			String time = zonedDateTimeUtil.toString(localZonedDateTime);

			String message = blimp.getMessage(OutputMessage.ISS_LOCATION, time,
					locationNowResponse.getIssPosition());
			LOGGER.info(message);
		}
	}

	private void logSpeed(LocationNowResponse locationNowResponse) {
		if (LOGGER.isInfoEnabled() && previousLocationResponse.isPresent()) {

			long previousTimestamp = previousLocationResponse.get()
					.getTimestamp();
			long currentTimestamp = locationNowResponse.getTimestamp();
			Duration duration = Duration.ofSeconds(currentTimestamp - previousTimestamp);
			
			double issSpeed = calculateIssSpeed(previousLocationResponse.get()
					.getIssPosition(), locationNowResponse.getIssPosition(), duration);

			String message = blimp.getMessage(OutputMessage.ISS_SPEED, issSpeed);
			LOGGER.info(message);
		}
	}

	private double calculateIssSpeed(Location previousLocation, Location currentLocation,
			Duration duration) {

		return unitsBo.calculateSpeedKmh(previousLocation, currentLocation,
				ISS_FLIGHT_ALTITUDE_METERS, duration);
	}
}
