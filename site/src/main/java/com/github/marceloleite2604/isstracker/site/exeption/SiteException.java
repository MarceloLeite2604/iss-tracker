package com.github.marceloleite2604.isstracker.site.exeption;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.site.util.SiteApplicationContext;
import com.github.marceloleite2604.isstracker.site.util.message.ErrorMessage;

public class SiteException extends Exception {

	private static final long serialVersionUID = 1L;

	protected static final Blimp BLIMP = SiteApplicationContext.getBlimp();

	public SiteException(Throwable cause, ErrorMessage errorMessage, Object... parameters) {
		this(cause, errorMessage.getCode(), parameters);
	}

	public SiteException(ErrorMessage errorMessage, Object... parameters) {
		this(errorMessage.getCode(), parameters);
	}

	public SiteException(String errorMessageCode, Object... parameters) {
		super(BLIMP.getMessage(errorMessageCode, parameters));
	}

	public SiteException(Throwable cause, String errorMessageCode, Object... parameters) {
		super(BLIMP.getMessage(errorMessageCode, parameters), cause);
	}

}
