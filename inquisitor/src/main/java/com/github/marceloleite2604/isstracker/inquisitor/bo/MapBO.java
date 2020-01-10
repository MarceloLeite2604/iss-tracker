package com.github.marceloleite2604.isstracker.inquisitor.bo;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.commons.dao.MapDAO;
import com.github.marceloleite2604.isstracker.commons.model.Coordinates;
import com.github.marceloleite2604.isstracker.commons.model.db.IssPosition;
import com.github.marceloleite2604.isstracker.commons.model.db.Map;
import com.github.marceloleite2604.isstracker.inquisitor.exception.GoogleApiUsageException;
import com.github.marceloleite2604.isstracker.inquisitor.properties.RouteMapGenerationProperties;
import com.github.marceloleite2604.isstracker.inquisitor.util.google.GoogleMapsStaticUtil;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class MapBO {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapBO.class);

	@Inject
	private MapDAO mapDAO;

	@Inject
	private GoogleMapsStaticUtil googleMapsStaticUtil;

	@Inject
	private IssPositionBO issPositionBO;

	@Inject
	private Blimp blimp;

	@Inject
	private RouteMapGenerationProperties routeMapGenerationProperties;

	public Map save(Map map) {
		return mapDAO.save(map);
	}

	public Optional<Map> findMostRecent() {
		return mapDAO.findTop1ByOrderByInstantDesc();
	}

	public Optional<Map> generateMap(List<IssPosition> issPositions) {

		if (!shouldGenerateNewMap(findMostRecent()) || !canGenerateNewMap(issPositions)) {
			return Optional.empty();
		}

		issPositionBO.sorByInstantDesc(issPositions);

		List<Coordinates> coordinates = issPositions.stream()
				.map(IssPosition::getCoordinates)
				.collect(Collectors.toList());

		LocalDateTime instant = issPositions.get(0)
				.getInstant();

		Optional<Map> optionalMap;

		try {
			byte[] data = googleMapsStaticUtil.retrieveMapWithCoordinates(coordinates);

			Map map = Map.builder()
					.instant(instant)
					.data(data)
					.build();

			optionalMap = Optional.of(map);

		} catch (GoogleApiUsageException exception) {
			String message = blimp.getMessage(ErrorMessage.ERROR_GENERATING_MAP);
			LOGGER.error(message, exception);
			optionalMap = Optional.empty();
		}

		return optionalMap;
	}

	private boolean shouldGenerateNewMap(Optional<Map> optionalPreviousMap) {

		if (optionalPreviousMap.isEmpty()) {
			return true;
		}

		LocalDateTime instant = optionalPreviousMap.get()
				.getInstant();

		LocalDateTime limitTime = ZonedDateTime.now()
				.withZoneSameInstant(ZoneId.of("UTC"))
				.toLocalDateTime()
				.minusMinutes(routeMapGenerationProperties.getUpdateTimeMinutes());

		return instant.isBefore(limitTime);
	}

	private boolean canGenerateNewMap(List<IssPosition> issPositions) {
		return (!CollectionUtils.isEmpty(issPositions)
				&& issPositions.size() > routeMapGenerationProperties.getMinimalPositions());
	}
}
