package com.github.marceloleite2604.isstracker.site.service;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.commons.model.db.RouteMap;
import com.github.marceloleite2604.isstracker.site.bo.MapBO;
import com.github.marceloleite2604.isstracker.site.util.message.ErrorMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);

	@Inject
	private Blimp blimp;

	@Inject
	private MapBO mapBO;

	public void get(HttpServletResponse httpServletResponse) {

		Optional<RouteMap> optionalMap = mapBO.findMostRecent();

		if (optionalMap.isPresent()) {
			RouteMap map = optionalMap.get();
			if (isRecentMap(map)) {
				returnMap(map, httpServletResponse);
			} else {
				returnMapUnavailable(httpServletResponse);
			}
		} else {
			returnMapUnavailable(httpServletResponse);
		}

	}

	private boolean isRecentMap(RouteMap map) {
		LocalDateTime nowAtZoneId = ZonedDateTime.now()
				.withZoneSameInstant(ZoneId.of("UTC"))
				.toLocalDateTime();
		return map.getInstant()
				.isAfter(nowAtZoneId.minusMinutes(10));
	}

	private void returnMap(RouteMap map, HttpServletResponse httpServletResponse) {
		try (InputStream inputStream = new ByteArrayInputStream(map.getData())) {

			IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
		} catch (IOException exception) {
			String message = blimp.getMessage(ErrorMessage.ERROR_RETRIEVING_MAP);
			LOGGER.error(message, exception);
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	private void returnMapUnavailable(HttpServletResponse httpServletResponse) {
		httpServletResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
	}

}
