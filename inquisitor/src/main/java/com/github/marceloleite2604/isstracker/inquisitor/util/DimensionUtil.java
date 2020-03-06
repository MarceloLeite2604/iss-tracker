package com.github.marceloleite2604.isstracker.inquisitor.util;

import com.figtreelake.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DimensionUtil {

	private static final String WIDTH_REGEX_GROUP = "width";

	private static final String HEIGHT_REGEX_GROUP = "height";

	private static final String DIMENSION_REGEX_TEMPLATE = "(?<%s>[0-9]+)x(?<%s>[0-9]+)";

	private static final Pattern PATTERN_DIMENSION = Pattern.compile(
			String.format(DIMENSION_REGEX_TEMPLATE, WIDTH_REGEX_GROUP, HEIGHT_REGEX_GROUP));

	@Inject
	private Blimp blimp;

	public Dimension parse(String dimensionString) {
		if (StringUtils.isBlank(dimensionString)) {
			String message = blimp.getMessage(ErrorMessage.DIMENSION_CANNOT_BE_EMPTY);
			throw new IllegalArgumentException(message);
		}

		Matcher matcher = PATTERN_DIMENSION.matcher(dimensionString);

		if (!matcher.matches()) {
			String message = blimp.getMessage(ErrorMessage.INVALID_HEXADECIMAL_COLOR,
					dimensionString);
			throw new IllegalArgumentException(message);
		}

		String stringWidth = matcher.group(WIDTH_REGEX_GROUP);
		int with = Integer.parseInt(stringWidth);

		String stringHeight = matcher.group(HEIGHT_REGEX_GROUP);
		int height = Integer.parseInt(stringHeight);

		return new Dimension(with, height);
	}
}
