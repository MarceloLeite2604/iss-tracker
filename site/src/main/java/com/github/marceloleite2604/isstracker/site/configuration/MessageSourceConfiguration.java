package com.github.marceloleite2604.isstracker.site.configuration;

import com.github.marceloleite2604.isstracker.site.util.message.ErrorMessage;
import com.github.marceloleite2604.isstracker.site.util.message.SiteMessage;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageSourceConfiguration {

	private static final String MESSAGE_FILE_BASE_DIRECTORY_PATH = "classpath:i18n/messages/";

	@Bean(BeanNames.MESSAGE_SOURCE)
	public MessageSource createMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		List<String> messageFilePaths = Arrays.asList(SiteMessage.FILE_PATH,
				ErrorMessage.FILE_PATH);

		messageFilePaths = messageFilePaths.stream()
				.map(messageFilePath -> MESSAGE_FILE_BASE_DIRECTORY_PATH + messageFilePath )
				.collect(Collectors.toList());

		messageSource.setBasenames(messageFilePaths.toArray(String[]::new));
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

}
