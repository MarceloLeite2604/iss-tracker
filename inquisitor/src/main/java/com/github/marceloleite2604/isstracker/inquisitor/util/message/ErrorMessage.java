package com.github.marceloleite2604.isstracker.inquisitor.util.message;

import com.figtreelake.blimp.Message;
import java.io.File;

public enum ErrorMessage implements Message {

	STATUS_CODE_MESSAGE_EXCHANGING_ERROR(1),
	ERROR_EXCHANGING(2),
	ERROR_RETRIEVING_ISS_LOCATION_NOW(3),
	ERROR_RETRIEVING_ISS_PASS_TIMES(4),
	ERROR_RETRIEVING_ISS_ASTRONAUTS(5),
	ERROR_RETRIEVING_GOOGLE_API_KEY(6),
	ERROR_ELABORATING_URI_MAPS_STATIC_API(7),
	ERROR_GENERATING_MAP(8),
	HEXADECIMAL_COLOR_CANNOT_BE_EMPTY(9),
	INVALID_HEXADECIMAL_COLOR(10),
	DIMENSION_CANNOT_BE_EMPTY(11),
	INVALID_DIMENSION(12);

	public static final String FILE_PATH = "error/error".replace("/", File.separator);

	public static final String MESSAGE_CODE_TEMPLATE = "error.%d";

	private int code;

	private ErrorMessage(int code) {
		this.code = code;
	}

	public String getCode() {
		return String.format(MESSAGE_CODE_TEMPLATE, code);
	}

}
