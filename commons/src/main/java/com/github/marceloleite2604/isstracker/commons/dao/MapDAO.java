package com.github.marceloleite2604.isstracker.commons.dao;

import com.github.marceloleite2604.isstracker.commons.dao.repository.MapRepository;
import com.github.marceloleite2604.isstracker.commons.model.db.Map;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class MapDAO {

	@Inject
	private MapRepository mapRepository;

	public Map save(Map map) {
		return mapRepository.save(map);
	}
	
	public Optional<Map> findTop1ByOrderByInstantDesc() {
		return mapRepository.findTop1ByOrderByInstantDesc();
	}
}
