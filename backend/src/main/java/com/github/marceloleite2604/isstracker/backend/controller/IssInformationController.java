package com.github.marceloleite2604.isstracker.backend.controller;

import com.github.marceloleite2604.isstracker.backend.model.dto.IssInformationDto;
import com.github.marceloleite2604.isstracker.backend.service.IssInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(IssInformationController.PATH)
@Schema(name="iss-information")
public class IssInformationController {

  @SuppressWarnings("squid:S1075")
  public static final String PATH = "/iss-information";

  private final IssInformationService issInformationService;

  @Inject
  public IssInformationController(
      IssInformationService issInformationService) {
    this.issInformationService = issInformationService;
  }

  @Operation(summary = "Retrieve ISS route information.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Information found.",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = IssInformationDto.class))),
      @ApiResponse(responseCode = "503", description = "Information is not available at the time.",
          content = @Content(mediaType = "text/plain"))})
  @GetMapping
  public IssInformationDto getIssInformation() {
    return issInformationService.getIssInformation();
  }
}
