package com.github.marceloleite2604.isstracker.site.exeption;

import com.github.marceloleite2604.isstracker.site.util.message.ErrorMessage;

public class SiteHttpRequestException extends SiteException {

	private static final long serialVersionUID = 1L;

	public SiteHttpRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiteHttpRequestException(String message) {
		super(message);
	}

	public SiteHttpRequestException(ErrorMessage errorMessage, Object... parameters) {
		super(errorMessage, parameters);
	}

	public SiteHttpRequestException(String errorMessageCode, Object... parameters) {
		super(errorMessageCode, parameters);
	}

	public SiteHttpRequestException(Throwable cause, ErrorMessage errorMessage,
			Object... parameters) {
		super(cause, errorMessage, parameters);
	}

	public SiteHttpRequestException(Throwable cause, String errorMessageCode,
			Object... parameters) {
		super(cause, errorMessageCode, parameters);
	}

}
