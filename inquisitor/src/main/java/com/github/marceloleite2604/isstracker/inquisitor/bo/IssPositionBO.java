package com.github.marceloleite2604.isstracker.inquisitor.bo;

import com.figtreelake.blimp.Blimp;
import com.github.marceloleite2604.isstracker.commons.dao.IssPositionDAO;
import com.github.marceloleite2604.isstracker.commons.model.comparator.IssPositionByInstantDescComparator;
import com.github.marceloleite2604.isstracker.commons.model.db.IssPosition;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.OutputMessage;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IssPositionBO {

	private static final Logger LOGGER = LoggerFactory.getLogger(IssPositionBO.class);

	@Inject
	private IssPositionByInstantDescComparator issPositionByInstantDescComparator;

	@Inject
	private IssPositionDAO issPositionDAO;

	@Inject
	private Blimp blimp;

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

	public long deleteByInstantLessThan(LocalDateTime instant) {
		Long deletedRows = issPositionDAO.deleteByInstantLessThan(instant);

		if (Objects.isNull(deletedRows)) {
			String message = blimp
					.getMessage(OutputMessage.NULL_RETURNED_FROM_ISS_POSITION_DELETION_REQUEST);
			LOGGER.warn(message);
			deletedRows = 0L;
		}

		return deletedRows;
	}
}
