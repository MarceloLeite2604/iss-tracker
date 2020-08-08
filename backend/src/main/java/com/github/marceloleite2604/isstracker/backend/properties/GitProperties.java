package com.github.marceloleite2604.isstracker.backend.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(PropertiesPath.GIT)
@Validated
public class GitProperties {

  private String commitId;

  private String commitIdAbbrev;

  private String commitTime;

  public String getCommitId() {
    return commitId;
  }

  public void setCommitId(String commitId) {
    this.commitId = commitId;
  }

  public String getCommitIdAbbrev() {
    return commitIdAbbrev;
  }

  public void setCommitIdAbbrev(String commitIdAbbrev) {
    this.commitIdAbbrev = commitIdAbbrev;
  }

  public String getCommitTime() {
    return commitTime;
  }

  public void setCommitTime(String commitTime) {
    this.commitTime = commitTime;
  }
}
