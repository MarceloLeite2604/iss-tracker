package com.github.marceloleite2604.isstracker.inquisitor.properties;

import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.GOOGLE)
@Validated
public class GoogleProperties {

	/**
	 * Environment variable name which contains Google API Key.
	 */
	@NotBlank
	private String apiKeyEnvironmentVariable;
	
	/**
	 * Google maps static API URL.
	 */
	@NotBlank
	private String mapsStaticApiUrl;

	public String getApiKeyEnvironmentVariable() {
		return apiKeyEnvironmentVariable;
	}

	public void setApiKeyEnvironmentVariable(String apiKeyEnvironmentVariable) {
		this.apiKeyEnvironmentVariable = apiKeyEnvironmentVariable;
	}

	public String getMapsStaticApiUrl() {
		return mapsStaticApiUrl;
	}

	public void setMapsStaticApiUrl(String mapsStaticApiUrl) {
		this.mapsStaticApiUrl = mapsStaticApiUrl;
	}
}
