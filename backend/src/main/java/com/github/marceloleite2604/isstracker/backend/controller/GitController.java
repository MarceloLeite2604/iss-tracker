package com.github.marceloleite2604.isstracker.backend.controller;

import com.github.marceloleite2604.isstracker.backend.model.dto.GitPropertiesDto;
import com.github.marceloleite2604.isstracker.backend.model.dto.IssInformationDto;
import com.github.marceloleite2604.isstracker.backend.model.mapper.GitPropertiesToGitPropertiesDtoMapper;
import com.github.marceloleite2604.isstracker.backend.properties.GitProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GitController.PATH)
@Schema(name="git")
public class GitController {

  @SuppressWarnings("squid:S1075")
  public static final String PATH = "/git";

  private final GitPropertiesDto gitPropertiesDto;

  public GitController(GitProperties gitProperties, GitPropertiesToGitPropertiesDtoMapper gitPropertiesToGitPropertiesDtoMapper) {
    this.gitPropertiesDto = gitPropertiesToGitPropertiesDtoMapper.map(gitProperties);
  }

  /*
  The project uses "git-commit-id-plugin" Maven plugin to retrieve Git information from ".git" reository, but, unfortunatelly, Heroku does not keep this directory in its Dyno during project building, causing its usage to fail. Hence, it is not possible to use this endpoint through it (but perhaps somewhere in the future).
  @Operation(summary = "Retrieve Git information about the project.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Information found.",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = IssInformationDto.class)))})
  @GetMapping
  public GitPropertiesDto get() {
    return gitPropertiesDto;
  }
  */
}
