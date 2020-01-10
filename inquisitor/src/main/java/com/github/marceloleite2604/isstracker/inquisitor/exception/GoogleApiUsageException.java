package com.github.marceloleite2604.isstracker.inquisitor.exception;

import com.github.marceloleite2604.isstracker.commons.exception.IssTrackerException;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;

public class GoogleApiUsageException extends IssTrackerException {

	private static final long serialVersionUID = 1L;

	public GoogleApiUsageException(Throwable throwable, ErrorMessage errorMessage) {
		super(throwable, errorMessage);
	}

}
