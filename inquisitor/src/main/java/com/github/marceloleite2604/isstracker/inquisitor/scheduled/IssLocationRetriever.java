package com.github.marceloleite2604.isstracker.inquisitor.scheduled;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.bo.IssApiBO;
import com.github.marceloleite2604.isstracker.inquisitor.bo.IssPositionBO;
import com.github.marceloleite2604.isstracker.inquisitor.model.db.IssPosition;
import com.github.marceloleite2604.isstracker.inquisitor.model.mapper.LocationNowResponseToIssPositionMapper;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.locationnow.LocationNowResponse;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.OutputMessage;
import java.util.Objects;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IssLocationRetriever {

	private static final Logger LOGGER = LoggerFactory.getLogger(IssLocationRetriever.class);

	private static final long EXECUTION_PERIOD_MILLIS = 60000;

	@Inject
	private IssApiBO issApiBO;

	@Inject
	private IssPositionBO issPositionBO;

	@Inject
	private Blimp blimp;

	@Inject
	private LocationNowResponseToIssPositionMapper locationNowResponseToIssPositionMapper;

	private Optional<LocationNowResponse> previousLocationResponse;

	public IssLocationRetriever() {
		previousLocationResponse = Optional.empty();
	}

	@Scheduled(fixedDelay = EXECUTION_PERIOD_MILLIS)
	public void retrieve() {
		Optional<LocationNowResponse> locationNowResponse = issApiBO.locationNow();
		locationNowResponse.ifPresent(this::processLocationNowResponse);
		previousLocationResponse = locationNowResponse;
	}

	private void processLocationNowResponse(LocationNowResponse locationNowResponse) {
		IssPosition issPosition = locationNowResponseToIssPositionMapper.map(locationNowResponse,
				previousLocationResponse);
		log(issPosition);
		saveIssPosition(issPosition);
	}

	private void saveIssPosition(IssPosition issPosition) {
		issPositionBO.save(issPosition);
	}

	private void log(IssPosition issPosition) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(elaborateLogMessage(issPosition));
		}
	}

	private String elaborateLogMessage(IssPosition issPosition) {

		String message;

		if (Objects.isNull(issPosition.getSpeed())) {
			message = blimp.getMessage(OutputMessage.ISS_LOCATION, issPosition.getCoordinates()
					.getLatitude(),
					issPosition.getCoordinates()
							.getLongitude());
		} else {
			message = blimp.getMessage(OutputMessage.ISS_LOCATION_AND_SPEED,
					issPosition.getCoordinates()
							.getLatitude(),
					issPosition.getCoordinates()
							.getLongitude(),
					issPosition.getSpeed());
		}
		return message;
	}
}
