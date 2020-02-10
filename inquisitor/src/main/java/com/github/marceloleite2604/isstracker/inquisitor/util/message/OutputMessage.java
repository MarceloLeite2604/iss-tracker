package com.github.marceloleite2604.isstracker.inquisitor.util.message;

import com.github.marceloleite2604.blimp.Message;
import java.io.File;

public enum OutputMessage implements Message {

	ISS_LOCATION(1),
	ISS_LOCATION_AND_SPEED(2),
	NEW_MAP_GENERATED(3),
	NULL_RETURNED_FROM_ISS_POSITION_DELETION_REQUEST(4),
	OLD_ISS_POSITIONS_DELETION_START(5),
	OLD_ISS_POSITIONS_DELETION_RESULT(6);

	public static final String FILE_PATH = "output/output".replace("/", File.separator);

	private static final String MESSAGE_CODE_TEMPLATE = "output.%d";

	private int code;

	private OutputMessage(int code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return String.format(MESSAGE_CODE_TEMPLATE, code);
	}
}
