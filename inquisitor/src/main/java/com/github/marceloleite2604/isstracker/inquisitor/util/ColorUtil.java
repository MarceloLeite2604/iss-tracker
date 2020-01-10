package com.github.marceloleite2604.isstracker.inquisitor.util;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ColorUtil {

	private static final String HEX_RGBA_TEMPLATE = "0x%02x%02x%02x%02x";

	private static final String RED_REGEX_GROUP = "red";

	private static final String GREEN_REGEX_GROUP = "green";

	private static final String BLUE_REGEX_GROUP = "blue";

	private static final String ALPHA_REGEX_GROUP = "alpha";

	private static final String REGEX_RGBA_HEXA_STRING_TEMPLATE = "0x(?<%s>[0-9a-f]{2})(?<%s>[0-9a-f]{2})(?<%s>[0-9a-f]{2})(?<%s>[0-9a-f]{2})";

	private static final Pattern PATTERN_RGBA_HEXA = Pattern
			.compile(String.format(REGEX_RGBA_HEXA_STRING_TEMPLATE, RED_REGEX_GROUP,
					GREEN_REGEX_GROUP, BLUE_REGEX_GROUP, ALPHA_REGEX_GROUP));

	private static final float MAX_VALUE_COLOR = 255f;

	@Inject
	private IntegerUtil integerUtil;

	@Inject
	private Blimp blimp;

	public String toHexRGBA(Color color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		int alpha = color.getAlpha();

		return String.format(HEX_RGBA_TEMPLATE, red, green, blue, alpha);
	}

	public Color toColor(String rgbaHexadecimal) {

		if (StringUtils.isBlank(rgbaHexadecimal)) {
			String message = blimp.getMessage(ErrorMessage.HEXADECIMAL_COLOR_CANNOT_BE_EMPTY);
			throw new IllegalArgumentException(message);
		}

		Matcher matcher = PATTERN_RGBA_HEXA.matcher(rgbaHexadecimal);

		if (!matcher.matches()) {
			String message = blimp.getMessage(ErrorMessage.INVALID_HEXADECIMAL_COLOR,
					rgbaHexadecimal);
			throw new IllegalArgumentException(message);
		}

		String hexStringRed = matcher.group(RED_REGEX_GROUP);
		float red = retrieveColorValue(hexStringRed);

		String hexStringGreen = matcher.group(GREEN_REGEX_GROUP);
		float green = retrieveColorValue(hexStringGreen);

		String hexStringBlue = matcher.group(BLUE_REGEX_GROUP);
		float blue = retrieveColorValue(hexStringBlue);

		String hexStringAlpha = matcher.group(ALPHA_REGEX_GROUP);
		float alpha = retrieveColorValue(hexStringAlpha);

		return new Color(red, green, blue, alpha);
	}

	private float retrieveColorValue(String colorHexString) {
		float value = (float) integerUtil.parseFromHexString(colorHexString);
		return value / MAX_VALUE_COLOR;
	}
}
