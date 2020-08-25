package com.github.marceloleite2604.isstracker.backend.bo;

import com.github.marceloleite2604.isstracker.commons.dao.RouteMapDAO;
import com.github.marceloleite2604.isstracker.commons.model.RouteMap;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class MapBo {

  private RouteMapDAO routeMapDAO;

  @Inject
  public MapBo(RouteMapDAO routeMapDAO) {
    this.routeMapDAO = routeMapDAO;
  }

  public Optional<RouteMap> findMostRecent() {
    return routeMapDAO.findTop1ByOrderByInstantDesc();
  }
}
