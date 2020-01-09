package com.github.marceloleite2604.isstracker.site.util;

import java.awt.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorUtil {

	private static final String HEX_RGBA_TEMPLATE = "0x%02x%02x%02x%02x";

	public String toHexRGBA(Color color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		int alpha = color.getAlpha();

		return String.format(HEX_RGBA_TEMPLATE, red, green, blue, alpha);
	}
}
