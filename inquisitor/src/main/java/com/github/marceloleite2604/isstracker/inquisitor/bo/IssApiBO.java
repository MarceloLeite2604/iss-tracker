package com.github.marceloleite2604.isstracker.inquisitor.bo;

import com.github.marceloleite2604.isstracker.commons.model.Coordinates;
import com.github.marceloleite2604.isstracker.inquisitor.dao.IssApiDAO;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.locationnow.LocationNowResponse;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes.PassTimesRequest;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes.PassTimesResponse;
import com.github.marceloleite2604.util.time.zoned.ZonedDateTimeUtil;
import java.time.ZonedDateTime;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class IssApiBO {

	@Inject
	private IssApiDAO issDAO;

	@Inject
	private ZonedDateTimeUtil zonedDateTimeUtil;

	public Optional<LocationNowResponse> locationNow() {
		return issDAO.locationNow();
	}

	public Optional<PassTimesResponse> passTimes(PassTimesRequest passTimesRequest) {
		return issDAO.passTimes(passTimesRequest);
	}

	public Optional<PassTimesResponse> passTimes(Coordinates location, int altitude,
			ZonedDateTime zonedDateTime, int passes) {
		
		long datetime = zonedDateTimeUtil.convertAsEpochTime(zonedDateTime);
		
		PassTimesRequest passTimesRequest = PassTimesRequest.builder()
				.altitude(altitude)
				.location(location)
				.datetime(datetime)
				.passes(passes)
				.build();
		
		return issDAO.passTimes(passTimesRequest);
	}
}
