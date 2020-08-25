package com.github.marceloleite2604.isstracker.commons.dao.repository;

import com.github.marceloleite2604.isstracker.commons.model.IssPosition;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssPositionRepository extends CrudRepository<IssPosition, LocalDateTime>{

	List<IssPosition> findAllByInstantBetweenOrderByInstantDesc(LocalDateTime start, LocalDateTime end);
	
	@Transactional
	Long deleteByInstantLessThan(LocalDateTime instant);

}
