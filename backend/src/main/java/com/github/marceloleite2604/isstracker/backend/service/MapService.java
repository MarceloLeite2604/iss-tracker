package com.github.marceloleite2604.isstracker.backend.service;

import com.figtreelake.blimp.Blimp;
import com.github.marceloleite2604.isstracker.backend.bo.MapBo;
import com.github.marceloleite2604.isstracker.backend.exception.IssRouteMapUnavailableException;
import com.github.marceloleite2604.isstracker.backend.model.message.ErrorMessage;
import com.github.marceloleite2604.isstracker.commons.model.RouteMap;
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

  private static final IssRouteMapUnavailableException ISS_ROUTE_MAP_UNAVAILABLE_EXCEPTION = new IssRouteMapUnavailableException(
      "ISS route map is unavailable right now. Please try again in a few minutes.");

  private final Blimp blimp;

  private final MapBo mapBo;

  @Inject
  public MapService(Blimp blimp, MapBo mapBo) {
    this.blimp = blimp;
    this.mapBo = mapBo;
  }


  public void get(HttpServletResponse httpServletResponse) {

    Optional<RouteMap> optionalMap = mapBo.findMostRecent();

    if (optionalMap.isPresent()) {
      RouteMap map = optionalMap.get();
      if (isRecentMap(map)) {
        returnMap(map, httpServletResponse);
      } else {
        throw ISS_ROUTE_MAP_UNAVAILABLE_EXCEPTION;
      }
    } else {
      throw ISS_ROUTE_MAP_UNAVAILABLE_EXCEPTION;
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

}
