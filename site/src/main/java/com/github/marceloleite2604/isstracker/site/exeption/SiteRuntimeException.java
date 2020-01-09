package com.github.marceloleite2604.isstracker.site.exeption;

public class SiteRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SiteRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiteRuntimeException(String message) {
		super(message);
	}

}
