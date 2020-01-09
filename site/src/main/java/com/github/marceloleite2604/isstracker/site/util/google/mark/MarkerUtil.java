package com.github.marceloleite2604.isstracker.site.util.google.mark;

import com.github.marceloleite2604.isstracker.site.model.Coordinates;
import com.github.marceloleite2604.isstracker.site.model.google.marker.Marker;
import com.github.marceloleite2604.isstracker.site.util.CoordinatesUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MarkerUtil {

	@Inject
	private MarkerStylesUtil markerStylesUtil;

	@Inject
	private CoordinatesUtil coordinatesUtil;

	public String writeValue(Marker marker) {
		return Arrays
				.asList(markerStylesUtil.writeValue(marker.getStyles()),
						writeCoordinates(marker.getCoordinates()))
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.joining("|"));

	}

	private Optional<String> writeCoordinates(List<Coordinates> coordinates) {
		String value = coordinates.stream()
				.map(coordinatesUtil::writeForUrlParameter)
				.filter(StringUtils::isNotEmpty)
				.collect(Collectors.joining("|"));

		return Optional.ofNullable(value);
	}

}
