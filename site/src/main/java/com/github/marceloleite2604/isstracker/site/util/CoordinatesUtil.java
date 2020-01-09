package com.github.marceloleite2604.isstracker.site.util;

import com.github.marceloleite2604.isstracker.site.model.Coordinates;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesUtil {

	private static final String COORDINATES_FOR_URL_PARAMETER_TEMPLATE = "%3.6f,%2.6f";

	public String writeForUrlParameter(Coordinates coordinates) {

		if (Objects.isNull(coordinates)) {
			return StringUtils.EMPTY;
		}

		return String.format(COORDINATES_FOR_URL_PARAMETER_TEMPLATE, coordinates.getLatitude(),
				coordinates.getLongitude());
	}
}
