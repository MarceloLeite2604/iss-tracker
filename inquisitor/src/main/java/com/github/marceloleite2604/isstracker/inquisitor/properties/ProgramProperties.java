package com.github.marceloleite2604.isstracker.inquisitor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.PROGRAM)
@Validated
public class ProgramProperties {

	/**
	 * ISS API Base URL.
	 */
	private String apiBaseUrl;

	/**
	 * Program locale.
	 */
	private String locale;

	/**
	 * Program time zone ID.
	 */
	private String zoneId;

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

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
