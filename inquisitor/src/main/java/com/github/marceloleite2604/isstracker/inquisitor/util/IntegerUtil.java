package com.github.marceloleite2604.isstracker.inquisitor.util;

import org.springframework.stereotype.Component;

@Component
public class IntegerUtil {

	private static final int HEXADECIMAL_RADIX = 16;

	public int parseFromHexString(String hexString) {

		return Integer.parseInt(hexString, HEXADECIMAL_RADIX);
	}
}
