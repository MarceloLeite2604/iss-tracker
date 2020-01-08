package com.github.marceloleite2604.isstracker.inquisitor.dao;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.exception.InquisitorHttpRequestException;
import com.github.marceloleite2604.isstracker.inquisitor.model.ExchangeRequest;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.astros.AstrosResponse;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.locationnow.LocationNowResponse;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes.PassTimesRequest;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes.PassTimesRequest.PathParameters;
import com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.passtimes.PassTimesResponse;
import com.github.marceloleite2604.isstracker.inquisitor.util.RestTemplateUtil;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class IssApiDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(IssApiDAO.class);

	private static final String LOCATION_NOW_PATH = "iss-now.json";

	private static final String ISS_PASS_PATH = "iss-pass.json";

	private static final String ASTROS_PATH = "astros.json";

	@Inject
	private RestTemplateUtil restTemplateUtil;

	@Inject
	private Blimp blimp;

	public Optional<LocationNowResponse> locationNow() {

		ExchangeRequest<Void, LocationNowResponse> exchangeRequest = ExchangeRequest
				.builder(new ParameterizedTypeReference<Void>() {
				}, new ParameterizedTypeReference<LocationNowResponse>() {
				})
				.url(LOCATION_NOW_PATH)
				.build();

		try {
			ResponseEntity<LocationNowResponse> responseEntity = restTemplateUtil
					.exchange(exchangeRequest);
			return Optional.of(responseEntity.getBody());
		} catch (InquisitorHttpRequestException exception) {
			LOGGER.error(blimp.getMessage(ErrorMessage.ERROR_RETRIEVING_ISS_LOCATION_NOW));
			return Optional.empty();
		}

	}

	public Optional<PassTimesResponse> passTimes(PassTimesRequest passTimesRequest) {
		ExchangeRequest<Void, PassTimesResponse> exchangeRequest = createPassTimeExchangeRequest(
				passTimesRequest);

		try {
			ResponseEntity<PassTimesResponse> responseEntity = restTemplateUtil
					.exchange(exchangeRequest);
			return Optional.of(responseEntity.getBody());
		} catch (InquisitorHttpRequestException exception) {
			LOGGER.error(blimp.getMessage(ErrorMessage.ERROR_RETRIEVING_ISS_PASS_TIMES));
			return Optional.empty();
		}
	}

	private ExchangeRequest<Void, PassTimesResponse> createPassTimeExchangeRequest(
			PassTimesRequest passTimeRequest) {

		List<NameValuePair> parameters = createPassTimeExchangeRequestParameters(passTimeRequest);

		return ExchangeRequest.builder(new ParameterizedTypeReference<Void>() {
		}, new ParameterizedTypeReference<PassTimesResponse>() {
		})
				.parameters(parameters)
				.url(ISS_PASS_PATH)
				.build();
	}

	private List<NameValuePair> createPassTimeExchangeRequestParameters(
			PassTimesRequest passTimesRequest) {
		List<NameValuePair> parameters = new ArrayList<>();

		parameters.add(new BasicNameValuePair(PathParameters.ALTITUDE,
				Integer.toString(passTimesRequest.getAltitude())));
		parameters.add(new BasicNameValuePair(PathParameters.LATITUDE,
				Double.toString(passTimesRequest.getLocation()
						.getLatitude())));
		parameters.add(new BasicNameValuePair(PathParameters.LONGITUDE,
				Double.toString(passTimesRequest.getLocation()
						.getLongitude())));
		parameters.add(new BasicNameValuePair(PathParameters.NUMBER,
				Integer.toString(passTimesRequest.getPasses())));

		return parameters;
	}

	public Optional<AstrosResponse> astronauts() {
		ExchangeRequest<Void, AstrosResponse> exchangeRequest = ExchangeRequest
				.builder(new ParameterizedTypeReference<Void>() {
				}, new ParameterizedTypeReference<AstrosResponse>() {
				})
				.url(ASTROS_PATH)
				.build();

		try {
			ResponseEntity<AstrosResponse> responseEntity = restTemplateUtil
					.exchange(exchangeRequest);
			return Optional.of(responseEntity.getBody());
		} catch (InquisitorHttpRequestException exception) {
			LOGGER.error(blimp.getMessage(ErrorMessage.ERROR_RETRIEVING_ISS_ASTRONAUTS));
			return Optional.empty();
		}
	}
}
