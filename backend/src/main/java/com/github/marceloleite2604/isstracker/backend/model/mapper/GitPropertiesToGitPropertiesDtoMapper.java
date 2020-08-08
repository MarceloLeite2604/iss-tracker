package com.github.marceloleite2604.isstracker.backend.model.mapper;

import com.github.marceloleite2604.isstracker.backend.model.dto.GitPropertiesDto;
import com.github.marceloleite2604.isstracker.backend.properties.GitProperties;
import org.springframework.stereotype.Component;

@Component
public class GitPropertiesToGitPropertiesDtoMapper {

  public GitPropertiesDto map(GitProperties gitProperties) {
    return GitPropertiesDto.Builder.aGitPropertiesDto()
        .commitId(gitProperties.getCommitId())
        .commitIdAbbrev(gitProperties.getCommitIdAbbrev())
        .commitTime(gitProperties.getCommitTime())
        .build();
  }
}
