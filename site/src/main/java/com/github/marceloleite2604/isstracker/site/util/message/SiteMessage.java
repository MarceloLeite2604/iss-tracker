package com.github.marceloleite2604.isstracker.site.util.message;

import com.figtreelake.blimp.Message;
import java.io.File;

public enum SiteMessage implements Message {

	ISS_LOCATION(1);

	public static final String FILE_PATH = "site/site".replace("/", File.separator);

	private static final String MESSAGE_CODE_TEMPLATE = "site.%d";

	private int code;

	private SiteMessage(int code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return String.format(MESSAGE_CODE_TEMPLATE, code);
	}
}
