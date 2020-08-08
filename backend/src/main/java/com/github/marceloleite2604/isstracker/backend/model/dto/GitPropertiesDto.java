package com.github.marceloleite2604.isstracker.backend.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "git-properties", description = "Properties from Git repository.")
public class GitPropertiesDto {

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


  public static final class Builder {

    private String commitId;
    private String commitIdAbbrev;
    private String commitTime;

    private Builder() {
    }

    public static Builder aGitPropertiesDto() {
      return new Builder();
    }

    public Builder commitId(String commitId) {
      this.commitId = commitId;
      return this;
    }

    public Builder commitIdAbbrev(String commitIdAbbrev) {
      this.commitIdAbbrev = commitIdAbbrev;
      return this;
    }

    public Builder commitTime(String commitTime) {
      this.commitTime = commitTime;
      return this;
    }

    public GitPropertiesDto build() {
      GitPropertiesDto gitPropertiesDto = new GitPropertiesDto();
      gitPropertiesDto.setCommitId(commitId);
      gitPropertiesDto.setCommitIdAbbrev(commitIdAbbrev);
      gitPropertiesDto.setCommitTime(commitTime);
      return gitPropertiesDto;
    }
  }
}
