package com.github.marceloleite2604.isstracker.inquisitor.util.google.path;

import com.github.marceloleite2604.isstracker.inquisitor.model.google.path.PathStyles;
import com.github.marceloleite2604.isstracker.inquisitor.properties.RouteMapStyleProperties;
import com.github.marceloleite2604.isstracker.inquisitor.util.ColorUtil;
import java.awt.Color;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PathStylesUtil {

	private static final int DEFAULT_PATH_WEIGHT = 4;

	private static final Color DEFAULT_PATH_COLOR = new Color(1.0f, 0.0f, 0.0f, 0.5f);

	@Inject
	private ColorUtil colorUtil;

	@Inject
	private RouteMapStyleProperties routeMapStyleProperties;

	private PathStyles pathGeneralStyles;

	public Optional<String> writeValue(PathStyles pathStyles) {

		if (Objects.isNull(pathStyles)) {
			return Optional.empty();
		}

		String value = Arrays
				.asList(writeWeightKey(pathStyles), writeColorKey(pathStyles),
						writeFillColorKey(pathStyles), writeGeodesic(pathStyles))
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.joining("|"));
		return Optional.ofNullable(value);

	}

	private Optional<String> writeWeightKey(PathStyles pathStyles) {
		return Optional.ofNullable(pathStyles.getWeight())
				.map(weight -> Key.WEIGHT + ":" + Integer.toString(weight));
	}

	private Optional<String> writeColorKey(PathStyles pathStyles) {
		return Optional.ofNullable(pathStyles.getColor())
				.map(color -> Key.COLOR + ":" + colorUtil.toHexRGBA(color));
	}

	private Optional<String> writeFillColorKey(PathStyles pathStyles) {
		return Optional.ofNullable(pathStyles.getFillColor())
				.map(fillColor -> Key.FILL_COLOR + ":" + colorUtil.toHexRGBA(fillColor));
	}

	private Optional<String> writeGeodesic(PathStyles pathStyles) {
		return Optional.ofNullable(pathStyles.getGeodesic())
				.map(geodesic -> Key.GEODESIC + ":" + geodesic.toString());
	}

	public PathStyles retrieveGeneralPathStyles() {
		if (Objects.isNull(pathGeneralStyles)) {
			pathGeneralStyles = createGeneralPathStyles();
		}

		return pathGeneralStyles;
	}

	private PathStyles createGeneralPathStyles() {

		Color pathColor = retrieveGeneralPathStylesColor();

		return PathStyles.builder()
				.weight(DEFAULT_PATH_WEIGHT)
				.color(pathColor)
				.geodesic(true)
				.build();
	}

	private Color retrieveGeneralPathStylesColor() {
		Color routeColor;
		if (!StringUtils.isBlank(routeMapStyleProperties.getRouteColor())) {
			routeColor = colorUtil.toColor(routeMapStyleProperties.getRouteColor());
		} else {
			routeColor = DEFAULT_PATH_COLOR;
		}
		return routeColor;
	}

	private class Key {
		static final String WEIGHT = "weight";
		private static final String COLOR = "color";
		private static final String FILL_COLOR = "fillcolor";
		private static final String GEODESIC = "geodesic";
	}

}
