package com.github.marceloleite2604.isstracker.inquisitor.dao;

import com.github.marceloleite2604.isstracker.inquisitor.dao.repository.IssPositionRepository;
import com.github.marceloleite2604.isstracker.inquisitor.model.db.IssPosition;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class IssPositionDAO {

	@Inject
	private IssPositionRepository issPositionRepository;
	
	public IssPosition save(IssPosition issPosition) {
		return issPositionRepository.save(issPosition);
	}
}
