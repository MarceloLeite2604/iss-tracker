package com.github.marceloleite2604.isstracker.site.dao.repository;

import com.github.marceloleite2604.isstracker.site.model.db.IssPosition;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface IssPositionRepository extends CrudRepository<IssPosition, LocalDateTime>{

	List<IssPosition> findAllByInstantBetweenOrderByInstantDesc(LocalDateTime start, LocalDateTime end);

}
