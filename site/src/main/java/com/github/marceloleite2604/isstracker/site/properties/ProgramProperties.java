package com.github.marceloleite2604.isstracker.site.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.PROGRAM)
@Validated
public class ProgramProperties {

	/**
	 * Program locale.
	 */
	private String locale;

	/**
	 * Program time zone ID.
	 */
	private String zoneId;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
}
