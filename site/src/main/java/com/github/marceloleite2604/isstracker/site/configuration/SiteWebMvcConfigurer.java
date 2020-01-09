package com.github.marceloleite2604.isstracker.site.configuration;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class SiteWebMvcConfigurer implements WebMvcConfigurer {

	@SuppressWarnings("squid:S1075")
	public static final String IMG_PATH_ANT_MATCHER = "/img/**";
	
	private static final CacheControl CACHE_CONTROL = CacheControl.maxAge(7, TimeUnit.DAYS);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler(IMG_PATH_ANT_MATCHER)
				.addResourceLocations("classpath:thymeleaf/static/img/")
				.setCacheControl(CACHE_CONTROL);
	}

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(createLocaleChangeInterceptor());
	}

	private LocaleChangeInterceptor createLocaleChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
}
