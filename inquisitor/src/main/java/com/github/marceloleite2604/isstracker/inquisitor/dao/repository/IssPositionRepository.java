package com.github.marceloleite2604.isstracker.inquisitor.dao.repository;

import com.github.marceloleite2604.isstracker.inquisitor.model.db.IssPosition;
import java.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface IssPositionRepository extends CrudRepository<IssPosition, LocalDateTime>{

}
