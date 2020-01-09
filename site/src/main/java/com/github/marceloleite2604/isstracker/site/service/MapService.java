package com.github.marceloleite2604.isstracker.site.service;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.site.bo.IssPositionBO;
import com.github.marceloleite2604.isstracker.site.exeption.GoogleApiUsageException;
import com.github.marceloleite2604.isstracker.site.model.Coordinates;
import com.github.marceloleite2604.isstracker.site.model.db.IssPosition;
import com.github.marceloleite2604.isstracker.site.util.google.GoogleMapsStaticUtil;
import com.github.marceloleite2604.isstracker.site.util.message.ErrorMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
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
	private IssPositionBO issPositionBO;

	@Inject
	private GoogleMapsStaticUtil googleMapsStaticUtil;

	public void get(HttpServletResponse httpServletResponse) {

		try (InputStream inputStream = new ByteArrayInputStream(retrieveMapImage())) {

			IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
		} catch (GoogleApiUsageException | IOException exception) {
			String message = blimp.getMessage(ErrorMessage.ERROR_RETRIEVING_MAP);
			LOGGER.error(message, exception);
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}

	private byte[] retrieveMapImage() throws GoogleApiUsageException {

		List<IssPosition> positions = issPositionBO.findLastHour();

		List<Coordinates> coordinates = positions.stream()
				.map(IssPosition::getCoordinates)
				.collect(Collectors.toList());

		return googleMapsStaticUtil.retrieveMapWithCoordinates(coordinates);
	}

}
