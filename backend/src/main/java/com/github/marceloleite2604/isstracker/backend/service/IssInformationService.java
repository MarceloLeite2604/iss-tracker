package com.github.marceloleite2604.isstracker.backend.service;

import com.github.marceloleite2604.isstracker.backend.bo.IssPositionBo;
import com.github.marceloleite2604.isstracker.backend.exception.IssInformationUnavailableException;
import com.github.marceloleite2604.isstracker.backend.model.dto.IssInformationDto;
import com.github.marceloleite2604.isstracker.backend.model.mapper.ListIssPositionToIssInformationDtoMapper;
import com.github.marceloleite2604.isstracker.commons.model.IssPosition;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class IssInformationService {

  private IssPositionBo issPositionBo;

  private ListIssPositionToIssInformationDtoMapper listIssPositionToIssInformationDtoMapper;

  @Inject
  public IssInformationService(IssPositionBo issPositionBo,
      ListIssPositionToIssInformationDtoMapper listIssPositionToIssInformationDtoMapper) {
    this.issPositionBo = issPositionBo;
    this.listIssPositionToIssInformationDtoMapper = listIssPositionToIssInformationDtoMapper;
  }

  public IssInformationDto getIssInformation() {
    List<IssPosition> issPositions = issPositionBo.findLastHour();
    if (doesNotHaveEnoughPositions(issPositions)) {
      throw new IssInformationUnavailableException("ISS information is not available right now. Please try again in a few minutes.");
    }
    return listIssPositionToIssInformationDtoMapper
        .map(issPositions);
  }

  private boolean doesNotHaveEnoughPositions(List<IssPosition> issPositions) {
    return (Objects.isNull(issPositions) || issPositions.size() < 2);
  }

}
