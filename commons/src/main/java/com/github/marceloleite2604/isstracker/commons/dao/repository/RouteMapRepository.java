package com.github.marceloleite2604.isstracker.commons.dao.repository;

import com.github.marceloleite2604.isstracker.commons.model.RouteMap;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RouteMapRepository extends CrudRepository<RouteMap, LocalDateTime>{

	public Optional<RouteMap> findTop1ByOrderByInstantDesc();

}
