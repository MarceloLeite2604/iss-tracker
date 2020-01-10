package com.github.marceloleite2604.isstracker.commons.exception;

public class IssTrackerRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IssTrackerRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IssTrackerRuntimeException(String message) {
		super(message);
	}

}
