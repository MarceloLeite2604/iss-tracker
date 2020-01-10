package com.github.marceloleite2604.isstracker.site.bo;

import com.github.marceloleite2604.isstracker.commons.dao.MapDAO;
import com.github.marceloleite2604.isstracker.commons.model.db.Map;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class MapBO {

	@Inject
	private MapDAO mapDAO;

	public Optional<Map> findMostRecent() {
		return mapDAO.findTop1ByOrderByInstantDesc();
	}
}
