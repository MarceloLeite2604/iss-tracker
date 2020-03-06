package com.github.marceloleite2604.isstracker.commons.exception;

import com.figtreelake.blimp.Blimp;
import com.figtreelake.blimp.Message;
import com.github.marceloleite2604.isstracker.commons.util.IssTrackerApplicationContext;

public class IssTrackerException extends Exception {

	private static final long serialVersionUID = 1L;

	protected static final Blimp BLIMP = IssTrackerApplicationContext.retrieveBean(Blimp.class);

	public IssTrackerException(Throwable cause, Message errorMessage, Object... parameters) {
		this(cause, errorMessage.getCode(), parameters);
	}

	public IssTrackerException(Message errorMessage, Object... parameters) {
		this(errorMessage.getCode(), parameters);
	}

	public IssTrackerException(String errorMessageCode, Object... parameters) {
		super(BLIMP.getMessage(errorMessageCode, parameters));
	}

	public IssTrackerException(Throwable cause, String errorMessageCode, Object... parameters) {
		super(BLIMP.getMessage(errorMessageCode, parameters), cause);
	}

}
