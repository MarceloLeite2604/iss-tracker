package com.github.marceloleite2604.isstracker.site.configuration;

import com.figtreelake.blimp.Blimp;
import com.figtreelake.sled.Sled;
import com.figtreelake.util.time.zoned.ZonedDateTimeUtil;
import com.github.marceloleite2604.isstracker.site.properties.EncryptionProperties;
import com.github.marceloleite2604.isstracker.site.properties.ProgramProperties;
import com.github.marceloleite2604.isstracker.site.util.message.ErrorMessage;
import com.github.marceloleite2604.isstracker.site.util.message.SiteMessage;
import java.io.File;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProgramConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProgramConfiguration.class);

	private static final String MESSAGES_FILES_ROOT_DIRECTORY = "classpath:i18n/messages/"
			.replace("/", File.separator);

	@Bean(BeanNames.LOCALE)
	public Locale createLocale(ProgramProperties programProperties) {
		Locale locale;
		String languageTag = programProperties.getLocale();
		if (!StringUtils.isBlank(languageTag)) {
			LOGGER.info("Setting program language to \"{}\".", languageTag);
			locale = Locale.forLanguageTag(languageTag);
		} else {
			locale = Locale.getDefault();
		}
		return locale;
	}

	@Bean(BeanNames.TIME_ZONE)
	public ZoneId createZoneId(ProgramProperties programProperties) {
		ZoneId zoneId;
		String stringZoneId = programProperties.getZoneId();
		if (!StringUtils.isBlank(stringZoneId)) {
			zoneId = ZoneId.of(stringZoneId);
			LOGGER.info("Setting zone ID to \"{}\".", zoneId.getId());

		} else {
			zoneId = ZoneId.systemDefault();
		}
		return zoneId;
	}

	@Bean(BeanNames.BLIMP)
	public Blimp createBlimp(Locale locale, ProgramProperties programProperties) {

		List<String> fileNames = Arrays.asList(ErrorMessage.FILE_PATH, SiteMessage.FILE_PATH);
		fileNames = fileNames.stream()
				.map(fileName -> MESSAGES_FILES_ROOT_DIRECTORY + fileName)
				.collect(Collectors.toList());

		return new Blimp(locale, fileNames);
	}

	@Bean(BeanNames.ZONED_DATE_TIME_UTIL)
	public ZonedDateTimeUtil createZonedDateTimeUtil() {
		return new ZonedDateTimeUtil();
	}

	@Bean(BeanNames.SLED)
	public Sled createSled(EncryptionProperties encryptionProperties) {
		return Sled.builder()
				.cryptographicAlgorithm(encryptionProperties.getCryptographicAlgorythm())
				.feedbackMode(encryptionProperties.getFeedbackMode())
				.paddingScheme(encryptionProperties.getPaddingScheme())
				.keyEnvironmentVariableName(encryptionProperties.getKeyEnvironmentVariableName())
				.build();
	}

	@Bean(BeanNames.LOCALE_RESOLVER)
	public LocaleResolver createLocaleResolver(Locale locale) {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(locale);
		return sessionLocaleResolver;
	}

}
