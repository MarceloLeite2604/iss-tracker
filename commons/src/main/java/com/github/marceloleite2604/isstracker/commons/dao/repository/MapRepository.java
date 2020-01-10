package com.github.marceloleite2604.isstracker.commons.dao.repository;

import com.github.marceloleite2604.isstracker.commons.model.db.Map;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MapRepository extends CrudRepository<Map, LocalDateTime>{

	public Optional<Map> findTop1ByOrderByInstantDesc();

}
