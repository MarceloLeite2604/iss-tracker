package com.github.marceloleite2604.isstracker.inquisitor.bo;

import com.github.marceloleite2604.isstracker.commons.dao.IssPositionDAO;
import com.github.marceloleite2604.isstracker.commons.model.comparator.IssPositionByInstantDescComparator;
import com.github.marceloleite2604.isstracker.commons.model.db.IssPosition;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class IssPositionBO {

	@Inject
	private IssPositionByInstantDescComparator issPositionByInstantDescComparator;

	@Inject
	private IssPositionDAO issPositionDAO;

	public IssPosition save(IssPosition issPosition) {
		return issPositionDAO.save(issPosition);
	}

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

	public void sorByInstantDesc(List<IssPosition> issPositions) {
		Collections.sort(issPositions, issPositionByInstantDescComparator);
	}
}
