package com.github.marceloleite2604.isstracker.backend.controller;

import com.github.marceloleite2604.isstracker.backend.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/map")
public class MapController {

  private final MapService mapService;

  @Inject
  public MapController(MapService mapService) {
    this.mapService = mapService;
  }

  @Operation(summary = "Retrieve an image containing the ISS route plotted on a map.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Image found.",
          content = @Content(mediaType = "image/png")),
      @ApiResponse(responseCode = "503", description = "The image is not available at the time.",
          content = @Content(mediaType = "text/plain")) })
  @GetMapping(produces = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE)
  public void get(HttpServletResponse httpServletResponse) {
    mapService.get(httpServletResponse);
  }
}
