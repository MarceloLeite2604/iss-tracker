package com.github.marceloleite2604.isstracker.site.configuration;

import com.github.marceloleite2604.isstracker.site.controller.PageController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IssTrackerWebMvcConfigurer implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/")
        .setViewName("forward:" + PageController.PATH);
		WebMvcConfigurer.super.addViewControllers(registry);
	}
}
