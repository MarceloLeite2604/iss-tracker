package com.github.marceloleite2604.isstracker.backend.bo;

import com.github.marceloleite2604.isstracker.commons.dao.RouteMapDAO;
import com.github.marceloleite2604.isstracker.commons.model.RouteMap;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class MapBo {

  private RouteMapDAO mapDAO;

  @Inject
  public MapBo(RouteMapDAO mapDAO) {
    this.mapDAO = mapDAO;
  }

  public Optional<RouteMap> findMostRecent() {
    return mapDAO.findTop1ByOrderByInstantDesc();
  }
}
