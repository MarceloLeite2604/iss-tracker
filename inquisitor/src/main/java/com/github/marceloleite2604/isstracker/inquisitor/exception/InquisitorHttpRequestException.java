package com.github.marceloleite2604.isstracker.inquisitor.exception;

import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;

public class InquisitorHttpRequestException extends InquisitorException {

	private static final long serialVersionUID = 1L;

	public InquisitorHttpRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public InquisitorHttpRequestException(String message) {
		super(message);
	}

	public InquisitorHttpRequestException(ErrorMessage errorMessage, Object... parameters) {
		super(errorMessage, parameters);
	}

	public InquisitorHttpRequestException(String errorMessageCode, Object... parameters) {
		super(errorMessageCode, parameters);
	}

	public InquisitorHttpRequestException(Throwable cause, ErrorMessage errorMessage,
			Object... parameters) {
		super(cause, errorMessage, parameters);
	}

	public InquisitorHttpRequestException(Throwable cause, String errorMessageCode,
			Object... parameters) {
		super(cause, errorMessageCode, parameters);
	}

}
