package com.github.marceloleite2604.isstracker.site.exeption;

import com.github.marceloleite2604.isstracker.site.util.message.ErrorMessage;

public class GoogleApiUsageException extends SiteException {

	private static final long serialVersionUID = 1L;

	public GoogleApiUsageException(Throwable throwable, ErrorMessage errorMessage) {
		super(throwable, errorMessage);
	}

}
