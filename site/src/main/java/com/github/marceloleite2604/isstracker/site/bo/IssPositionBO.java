package com.github.marceloleite2604.isstracker.site.bo;

import com.github.marceloleite2604.isstracker.commons.dao.IssPositionDAO;
import com.github.marceloleite2604.isstracker.commons.model.IssPosition;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class IssPositionBO {

	@Inject
	private IssPositionDAO issPositionDAO;

	public List<IssPosition> findAllByInstantBetweenOrderByInstantDesc(LocalDateTime start,
			LocalDateTime end) {
		return issPositionDAO.findAllByInstantBetweenOrderByInstantDesc(start, end);
	}

	public List<IssPosition> findLastHour() {
		LocalDateTime end = ZonedDateTime.now()
				.withZoneSameInstant(ZoneId.of("UTC"))
				.toLocalDateTime();
		LocalDateTime start = end.minusHours(1);
		return findAllByInstantBetweenOrderByInstantDesc(start, end);
	}
}
