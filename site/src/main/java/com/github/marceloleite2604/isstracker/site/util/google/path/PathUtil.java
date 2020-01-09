package com.github.marceloleite2604.isstracker.site.util.google.path;

import com.github.marceloleite2604.isstracker.site.model.Coordinates;
import com.github.marceloleite2604.isstracker.site.model.google.path.Path;
import com.github.marceloleite2604.isstracker.site.util.CoordinatesUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PathUtil {

	@Inject
	private PathStylesUtil pathStylesUtil;

	@Inject
	private CoordinatesUtil coordinatesUtil;

	public String writeValue(Path path) {
		return Arrays
				.asList(pathStylesUtil.writeValue(path.getStyles()),
						writePathLocations(path.getCoordinates()))
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.joining("|"));
	}

	private Optional<String> writePathLocations(List<Coordinates> pathLocations) {

		if (CollectionUtils.isEmpty(pathLocations)) {
			return Optional.empty();
		}

		String value = pathLocations.stream()
				.map(coordinatesUtil::writeForUrlParameter)
				.collect(Collectors.joining("|"));

		return Optional.ofNullable(value);
	}

}
