package com.github.marceloleite2604.isstracker.inquisitor.configuration;

import com.github.marceloleite2604.isstracker.inquisitor.properties.ProgramProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

@Configuration
public class RestTemplateConfiguration {

	@Bean(BeanNames.URI_TEMPLATE_HANDLER)
	public UriTemplateHandler createUriTemplateHandler(ProgramProperties programProperties) {
		return new DefaultUriBuilderFactory(programProperties.getApiBaseUrl());
	}

	@Bean(BeanNames.REST_TEMPLATE)
	public RestTemplate createRestTemplate(UriTemplateHandler uriTemplateHandler) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(uriTemplateHandler);
		return restTemplate;
	}

}
