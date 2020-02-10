package com.github.marceloleite2604.isstracker.inquisitor.scheduled;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.bo.IssPositionBO;
import com.github.marceloleite2604.isstracker.inquisitor.properties.RouteMapGenerationProperties;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.OutputMessage;
import com.github.marceloleite2604.util.time.zoned.ZonedDateTimeUtil;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OldIssPositionsDeleter {

	private static final Logger LOGGER = LoggerFactory.getLogger(OldIssPositionsDeleter.class);

	private static final long EXECUTION_PERIOD_MILLIS = 3600000;
	
	private static final long INITIAL_DELAY_MILLIS = 2000;

	@Inject
	private Blimp blimp;

	@Inject
	private IssPositionBO issPositionBO;

	@Inject
	private RouteMapGenerationProperties routeMapGenerationProperties;

	@Inject
	private ZonedDateTimeUtil zonedDateTimeUtil;

	@Scheduled(fixedDelay = EXECUTION_PERIOD_MILLIS, initialDelay = INITIAL_DELAY_MILLIS)
	public void deleteOldIssPositions() {
		ZonedDateTime limitInstant = retrieveLimitInstant();
		logPositionDeletionStart(limitInstant);
		long positionsDeleted = issPositionBO
				.deleteByInstantLessThan(limitInstant.toLocalDateTime());
		logPositionDeletionResult(positionsDeleted);
	}

	private ZonedDateTime retrieveLimitInstant() {
		return ZonedDateTime.now(ZoneOffset.UTC)
				.minusMinutes(routeMapGenerationProperties.getUpdateTimeMinutes());
	}

	private void logPositionDeletionStart(ZonedDateTime timeLimit) {
		if (LOGGER.isInfoEnabled()) {
			String stringTimeLimit = zonedDateTimeUtil.toString(timeLimit);
			String message = blimp.getMessage(OutputMessage.OLD_ISS_POSITIONS_DELETION_START,
					stringTimeLimit);
			LOGGER.info(message);
		}
	}

	private void logPositionDeletionResult(long positionsDeleted) {
		if (LOGGER.isInfoEnabled()) {
			String message = blimp.getMessage(OutputMessage.OLD_ISS_POSITIONS_DELETION_RESULT,
					positionsDeleted);
			LOGGER.info(message);
		}
	}

}
