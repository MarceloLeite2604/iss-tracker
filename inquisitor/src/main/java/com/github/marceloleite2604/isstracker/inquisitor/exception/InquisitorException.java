package com.github.marceloleite2604.isstracker.inquisitor.exception;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.inquisitor.util.InquisitorApplicationContext;
import com.github.marceloleite2604.isstracker.inquisitor.util.message.ErrorMessage;

public class InquisitorException extends Exception {

	private static final long serialVersionUID = 1L;

	protected static final Blimp BLIMP = InquisitorApplicationContext.getBlimp();

	public InquisitorException(Throwable cause, ErrorMessage errorMessage, Object... parameters) {
		this(cause, errorMessage.getCode(), parameters);
	}

	public InquisitorException(ErrorMessage errorMessage, Object... parameters) {
		this(errorMessage.getCode(), parameters);
	}

	public InquisitorException(String errorMessageCode, Object... parameters) {
		super(BLIMP.getMessage(errorMessageCode, parameters));
	}

	public InquisitorException(Throwable cause, String errorMessageCode, Object... parameters) {
		super(BLIMP.getMessage(errorMessageCode, parameters), cause);
	}

}
