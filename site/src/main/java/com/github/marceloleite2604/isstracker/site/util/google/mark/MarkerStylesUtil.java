package com.github.marceloleite2604.isstracker.site.util.google.mark;

import com.github.marceloleite2604.isstracker.site.model.google.marker.MarkerStyles;
import com.github.marceloleite2604.isstracker.site.util.ColorUtil;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class MarkerStylesUtil {

	@Inject
	private ColorUtil colorUtil;

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

	private class Key {
		static final String SIZE = "size";
		private static final String COLOR = "color";
		private static final String LABEL = "label";
	}
}
