package com.github.marceloleite2604.isstracker.inquisitor.bo;

import com.github.marceloleite2604.isstracker.inquisitor.dao.IssPositionDAO;
import com.github.marceloleite2604.isstracker.inquisitor.model.db.IssPosition;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class IssPositionBO {

	@Inject
	private IssPositionDAO issPositionDAO;
	
	public IssPosition save(IssPosition issPosition) {
		return issPositionDAO.save(issPosition);
	}
}
