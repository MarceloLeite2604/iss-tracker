package com.github.marceloleite2604.isstracker.site.util.google.path;

import com.github.marceloleite2604.isstracker.site.model.google.path.PathStyles;
import com.github.marceloleite2604.isstracker.site.util.ColorUtil;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class PathStylesUtil {

	@Inject
	private ColorUtil colorUtil;

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

	private class Key {
		static final String WEIGHT = "weight";
		private static final String COLOR = "color";
		private static final String FILL_COLOR = "fillcolor";
		private static final String GEODESIC = "geodesic";
	}

}
