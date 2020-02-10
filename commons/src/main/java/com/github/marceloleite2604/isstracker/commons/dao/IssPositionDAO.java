package com.github.marceloleite2604.isstracker.commons.dao;

import com.github.marceloleite2604.isstracker.commons.dao.repository.IssPositionRepository;
import com.github.marceloleite2604.isstracker.commons.model.db.IssPosition;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class IssPositionDAO {

  @Inject
  private IssPositionRepository issPositionRepository;

  public List<IssPosition> findAllByInstantBetweenOrderByInstantDesc(LocalDateTime start,
      LocalDateTime end) {
    return issPositionRepository.findAllByInstantBetweenOrderByInstantDesc(start, end);
  }

  public IssPosition save(IssPosition issPosition) {
    return issPositionRepository.save(issPosition);
  }

  public Long deleteByInstantLessThan(LocalDateTime instant) {
    return issPositionRepository.deleteByInstantLessThan(instant);
  }
}
