package com.github.marceloleite2604.isstracker.inquisitor.scheduled;

import com.figtreelake.blimp.Blimp;
import com.figtreelake.util.time.zoned.ZonedDateTimeUtil;
import com.github.marceloleite2604.isstracker.inquisitor.bo.RouteMapBO;
import com.github.marceloleite2604.isstracker.inquisitor.properties.RouteMapGenerationProperties;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.OutputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class OldRouteMapsDeleter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OldRouteMapsDeleter.class);

    private static final long EXECUTION_PERIOD_MILLIS = 3_600_000;

    private static final long INITIAL_DELAY_MILLIS = 4_000;

    @Inject
    private RouteMapGenerationProperties routeMapGenerationProperties;

    @Inject
    private ZonedDateTimeUtil zonedDateTimeUtil;

    @Inject
    private Blimp blimp;

    @Inject
    private RouteMapBO routeMapBO;

    @Scheduled(fixedDelay = EXECUTION_PERIOD_MILLIS, initialDelay = INITIAL_DELAY_MILLIS)
    public void deleteOldRouteMaps() {
        ZonedDateTime limitInstant = retrieveLimitInstant();
        logRouteMapsDeletionStart(limitInstant);
        long routeMapsDeleted = routeMapBO
                .deleteByInstantLessThan(limitInstant.toLocalDateTime());
        logRouteMapsDeletionResult(routeMapsDeleted);
    }

    private ZonedDateTime retrieveLimitInstant() {
        return ZonedDateTime.now(ZoneOffset.UTC)
                .minusMinutes(routeMapGenerationProperties.getUpdateTimeMinutes() * 2L);
    }

    private void logRouteMapsDeletionStart(ZonedDateTime timeLimit) {
        if (LOGGER.isInfoEnabled()) {
            String stringTimeLimit = zonedDateTimeUtil.toStringAsIsoOffsetDateTime(timeLimit);
            String message = blimp.getMessage(OutputMessage.OLD_ROUTE_MAPS_DELETION_START,
                    stringTimeLimit);
            LOGGER.info(message);
        }
    }

    private void logRouteMapsDeletionResult(long routeMapsDeleted) {
        if (LOGGER.isInfoEnabled()) {
            String message = blimp.getMessage(OutputMessage.OLD_ROUTE_MAPS_DELETION_RESULT,
                    routeMapsDeleted);
            LOGGER.info(message);
        }
    }
}
