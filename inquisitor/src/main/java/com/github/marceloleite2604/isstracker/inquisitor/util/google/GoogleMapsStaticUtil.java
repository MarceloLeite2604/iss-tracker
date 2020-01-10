package com.github.marceloleite2604.isstracker.inquisitor.util.google;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.commons.exception.IssTrackerRuntimeException;
import com.github.marceloleite2604.isstracker.commons.model.Coordinates;
import com.github.marceloleite2604.isstracker.inquisitor.exception.GoogleApiUsageException;
import com.github.marceloleite2604.isstracker.inquisitor.model.google.marker.Marker;
import com.github.marceloleite2604.isstracker.inquisitor.model.google.path.Path;
import com.github.marceloleite2604.isstracker.inquisitor.properties.GoogleProperties;
import com.github.marceloleite2604.isstracker.inquisitor.properties.RouteMapStyleProperties;
import com.github.marceloleite2604.isstracker.inquisitor.util.DimensionUtil;
import com.github.marceloleite2604.isstracker.inquisitor.util.google.mark.MarkerStylesUtil;
import com.github.marceloleite2604.isstracker.inquisitor.util.google.mark.MarkerUtil;
import com.github.marceloleite2604.isstracker.inquisitor.util.google.path.PathStylesUtil;
import com.github.marceloleite2604.isstracker.inquisitor.util.google.path.PathUtil;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleMapsStaticUtil {

	private static final Dimension DEFAULT_MAP_SIZE = new Dimension(800, 600);

	@Inject
	private GoogleProperties googleProperties;

	@Inject
	private Blimp blimp;

	@Inject
	private Locale locale;

	@Inject
	private PathUtil pathUtil;

	@Inject
	private MarkerUtil markerUtil;

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private PathStylesUtil pathStylesUtil;

	@Inject
	private MarkerStylesUtil markerStylesUtil;

	@Inject
	private DimensionUtil dimensionUtil;

	@Inject
	private RouteMapStyleProperties routeMapStyleProperties;

	private String apiKey;

	private Dimension mapSize;

	@PostConstruct
	public void postConstruct() {
		retrieveKey();
	}

	private String retrieveKey() {
		if (StringUtils.isEmpty(apiKey)) {
			apiKey = retrieveApiKeyFromEnvironmentVariable();
		}

		return apiKey;
	}

	private String retrieveApiKeyFromEnvironmentVariable() {
		String apiKeyFromEnvironmentVariable = System
				.getenv(googleProperties.getApiKeyEnvironmentVariable());

		if (StringUtils.isBlank(apiKeyFromEnvironmentVariable)) {
			String message = blimp.getMessage(ErrorMessage.ERROR_RETRIEVING_GOOGLE_API_KEY,
					googleProperties.getApiKeyEnvironmentVariable());
			throw new IssTrackerRuntimeException(message);
		}

		return apiKeyFromEnvironmentVariable;
	}

	public byte[] retrieveMapWithCoordinates(List<Coordinates> coordinates)
			throws GoogleApiUsageException {

		URI uri = elaborateStaticMapsUri(coordinates);

		try {
			return restTemplate.execute(uri, HttpMethod.GET, null, this::receiveContent);
		} catch (RestClientException exception) {
			throw new GoogleApiUsageException(exception,
					ErrorMessage.ERROR_RETRIEVING_GOOGLE_API_KEY);
		}
	}

	private byte[] receiveContent(ClientHttpResponse clientHttpResponse) {
		try {
			return StreamUtils.copyToByteArray(clientHttpResponse.getBody());
		} catch (IOException exception) {
			String message = blimp.getMessage(ErrorMessage.ERROR_RETRIEVING_GOOGLE_API_KEY,
					googleProperties.getApiKeyEnvironmentVariable());
			throw new RestClientException(message, exception);
		}
	}

	private URI elaborateStaticMapsUri(List<Coordinates> coordinates)
			throws GoogleApiUsageException {
		try {
			URIBuilder uriBuilder = new URIBuilder(googleProperties.getMapsStaticApiUrl());

			uriBuilder.addParameters(createStaticMapsUriParameters(coordinates));

			return uriBuilder.build();
		} catch (URISyntaxException exception) {
			throw new GoogleApiUsageException(exception,
					ErrorMessage.ERROR_ELABORATING_URI_MAPS_STATIC_API);
		}
	}

	private List<NameValuePair> createStaticMapsUriParameters(List<Coordinates> coordinates) {

		List<NameValuePair> parameters = new ArrayList<>();

		parameters.add(createParameterZoom());
		parameters.add(createParameterSize());
		parameters.add(createParameterMapType());
		parameters.add(createParameterLanguage());
		parameters.add(createParameterPath(coordinates));
		parameters.add(createParameterMarkers(coordinates));
		parameters.add(createParameterKey());

		return parameters;
	}

	private BasicNameValuePair createParameterZoom() {
		return new BasicNameValuePair(Parameters.ZOOM, Parameters.ZOOM_VALUE);
	}

	private BasicNameValuePair createParameterSize() {

		Dimension mapDimension = retrieveMapSize();

		String size = String.format(Parameters.SIZE_PARAMETER_VALUE_TEMPLATE,
				(int)mapDimension.getWidth(), (int)mapDimension.getHeight());
		return new BasicNameValuePair(Parameters.SIZE, size);
	}

	private Dimension retrieveMapSize() {
		if (Objects.isNull(mapSize)) {
			mapSize = createMapSize();
		}
		return mapSize;
	}

	private Dimension createMapSize() {

		Dimension mapSizeCreated;
		if (StringUtils.isBlank(routeMapStyleProperties.getImageSize())) {
			mapSizeCreated = DEFAULT_MAP_SIZE;
		} else {
			mapSizeCreated = dimensionUtil.parse(routeMapStyleProperties.getImageSize());
		}
		return mapSizeCreated;
	}

	private NameValuePair createParameterMapType() {
		return new BasicNameValuePair(Parameters.MAPTYPE, Parameters.MAPTYPE_VALUE);
	}

	private NameValuePair createParameterLanguage() {
		return new BasicNameValuePair(Parameters.LANGUAGE, locale.getLanguage());
	}

	private NameValuePair createParameterPath(List<Coordinates> coordinates) {

		Path path = Path.builder()
				.styles(pathStylesUtil.retrieveGeneralPathStyles())
				.coordinates(coordinates)
				.build();

		return new BasicNameValuePair(Parameters.PATH, pathUtil.writeValue(path));
	}

	private NameValuePair createParameterMarkers(List<Coordinates> coordinates) {
		Coordinates lastCoordinates = coordinates.get(0);

		Marker marker = Marker.builder()
				.styles(markerStylesUtil.retrieveGeneralMarkerStyles())
				.coordinates(Collections.singletonList(lastCoordinates))
				.build();

		return new BasicNameValuePair(Parameters.MARKERS, markerUtil.writeValue(marker));
	}

	private NameValuePair createParameterKey() {
		return new BasicNameValuePair(Parameters.KEY, apiKey);
	}

	private final class Parameters {

		private static final String ZOOM = "zoom";
		private static final String ZOOM_VALUE = "1";

		private static final String SIZE = "size";
		private static final String SIZE_PARAMETER_VALUE_TEMPLATE = "%dx%d";

		private static final String MAPTYPE = "maptype";
		private static final String MAPTYPE_VALUE = "satellite";

		private static final String LANGUAGE = "language";

		private static final String PATH = "path";

		private static final String MARKERS = "markers";

		private static final String KEY = "key";
	}
}
