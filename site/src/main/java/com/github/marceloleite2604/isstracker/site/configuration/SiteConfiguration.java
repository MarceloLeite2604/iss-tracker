package com.github.marceloleite2604.isstracker.site.configuration;

import com.github.marceloleite2604.isstracker.site.util.SitePaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteConfiguration {

	@Bean(BeanNames.SITE_PATHS_CLASS)
	public Class<SitePaths> createSitePathsClass() {
		return SitePaths.class;
	}
}
