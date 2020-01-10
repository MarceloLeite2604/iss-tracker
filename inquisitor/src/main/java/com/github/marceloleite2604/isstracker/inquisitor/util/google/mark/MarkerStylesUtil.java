package com.github.marceloleite2604.isstracker.inquisitor.util.google.mark;

import com.github.marceloleite2604.isstracker.inquisitor.model.google.marker.MarkerSize;
import com.github.marceloleite2604.isstracker.inquisitor.model.google.marker.MarkerStyles;
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
public class MarkerStylesUtil {

	private static final MarkerSize DEFAULT_MARKER_SIZE = MarkerSize.TINY;

	private static final Color DEFAULT_MARKER_COLOR = new Color(1.0f, 0.0f, 0.0f, 0.7f);

	@Inject
	private ColorUtil colorUtil;

	@Inject
	private RouteMapStyleProperties routeMapStyleProperties;

	private MarkerStyles generalMarkerStyles;

	public Optional<String> writeValue(MarkerStyles markerStyles) {

		if (Objects.isNull(markerStyles)) {
			return Optional.empty();
		}

		String value = Arrays
				.asList(writeSizeKey(markerStyles), writeColorKey(markerStyles),
						writeLabelKey(markerStyles))
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.joining("|"));
		return Optional.ofNullable(value);
	}

	private Optional<String> writeSizeKey(MarkerStyles markerStyles) {
		return Optional.ofNullable(markerStyles.getSize())
				.map(size -> Key.SIZE + ":" + size.getValue());
	}

	private Optional<String> writeColorKey(MarkerStyles markerStyles) {
		return Optional.ofNullable(markerStyles.getColor())
				.map(color -> Key.COLOR + ":" + colorUtil.toHexRGBA(color));
	}

	private Optional<String> writeLabelKey(MarkerStyles markerStyles) {
		return Optional.ofNullable(markerStyles.getLabel())
				.map(label -> Key.LABEL + ":" + label);
	}

	public MarkerStyles retrieveGeneralMarkerStyles() {
		if (Objects.isNull(generalMarkerStyles)) {
			generalMarkerStyles = createGeneralMarkerStyles();
		}

		return generalMarkerStyles;
	}

	private MarkerStyles createGeneralMarkerStyles() {

		Color markerColor = retrieveGeneralMarkerStylesColor();

		return MarkerStyles.builder()
				.size(DEFAULT_MARKER_SIZE)
				.color(markerColor)
				.build();
	}

	private Color retrieveGeneralMarkerStylesColor() {
		Color markerColor;
		if (!StringUtils.isBlank(routeMapStyleProperties.getRouteColor())) {
			markerColor = colorUtil.toColor(routeMapStyleProperties.getRouteColor());
		} else {
			markerColor = DEFAULT_MARKER_COLOR;
		}
		return markerColor;
	}

	private class Key {
		static final String SIZE = "size";
		private static final String COLOR = "color";
		private static final String LABEL = "label";
	}
}
