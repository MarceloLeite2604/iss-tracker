package com.github.marceloleite2604.isstracker.backend.configuration;

import com.github.marceloleite2604.isstracker.backend.properties.ProgramProperties;
import java.util.stream.Collectors;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Named(BeanNames.ISS_TRACKER_WEB_MVC_CONFIGURER)
public class IssTrackerWebMvcConfigurer implements WebMvcConfigurer {

  private static final Logger LOGGER = LoggerFactory.getLogger(IssTrackerWebMvcConfigurer.class);

  private final ProgramProperties programProperties;

  public IssTrackerWebMvcConfigurer(
      ProgramProperties programProperties) {
    this.programProperties = programProperties;
  }

  @Override
  public void addCorsMappings(CorsRegistry corsRegistry) {
    if (!CollectionUtils.isEmpty(programProperties.getCorsOrigins())) {
      String corsOrigins = String.join(", ", programProperties.getCorsOrigins());
      LOGGER.info("Enabling CORS requests for the following domains: {}", corsOrigins);
      corsRegistry
          .addMapping("/**")
          .allowedOrigins(programProperties.getCorsOrigins()
              .toArray(String[]::new));
    }
  }
}
