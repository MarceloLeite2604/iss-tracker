package com.github.marceloleite2604.isstracker.commons.dao;

import com.github.marceloleite2604.isstracker.commons.dao.repository.RouteMapRepository;
import com.github.marceloleite2604.isstracker.commons.model.RouteMap;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class RouteMapDAO {

	@Inject
	private RouteMapRepository routeMapRepository;

	public RouteMap save(RouteMap routeMap) {
		return routeMapRepository.save(routeMap);
	}

	public Optional<RouteMap> findTop1ByOrderByInstantDesc() {
		return routeMapRepository.findTop1ByOrderByInstantDesc();
	}

	public Long deleteByInstantLessThan(LocalDateTime instant) {
		return routeMapRepository.deleteByInstantLessThan(instant);
	}
}
