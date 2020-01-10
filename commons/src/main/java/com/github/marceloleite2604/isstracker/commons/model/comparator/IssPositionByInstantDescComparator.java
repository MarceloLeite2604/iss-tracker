package com.github.marceloleite2604.isstracker.commons.model.comparator;

import com.github.marceloleite2604.isstracker.commons.model.db.IssPosition;
import java.util.Comparator;
import org.springframework.stereotype.Component;

@Component
public class IssPositionByInstantDescComparator implements Comparator<IssPosition> {

	@Override
	public int compare(IssPosition first, IssPosition second) {
		return second.getInstant().compareTo(first.getInstant());
	}

}
