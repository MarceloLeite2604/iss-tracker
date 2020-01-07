package com.github.marceloleite2604.isstracker.inquisitor.exception;

public class InquisitorRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InquisitorRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InquisitorRuntimeException(String message) {
		super(message);
	}

}
