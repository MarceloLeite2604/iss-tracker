package com.github.marceloleite2604.isstracker.site.configuration;

import com.github.marceloleite2604.isstracker.site.properties.DatabaseProperties;
import com.github.marceloleite2604.isstracker.site.properties.EncryptionProperties;
import com.github.marceloleite2604.sled.Sled;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfiguration {

	private static final String PARAMETER_FORMAT_TEMPLATE = "%s=%s&";

	@SuppressWarnings("squid:S2095")
	@Bean(BeanNames.DATA_SOURCE)
	public DataSource criarDataSource(DatabaseProperties databaseProperties, Sled sled,
			EncryptionProperties encryptionProperties) {

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(databaseProperties.getDriverClassName());
		basicDataSource.setUrl(elaborateUrl(databaseProperties));
		basicDataSource.setUsername(databaseProperties.getUsername());

		String password = decryptPassword(sled, databaseProperties.getPassword(),
				encryptionProperties.getKey());
		basicDataSource.setPassword(password);

		basicDataSource.setMaxIdle(databaseProperties.getConnections());
		basicDataSource.setMaxTotal(databaseProperties.getConnections());

		return basicDataSource;
	}

	private String decryptPassword(Sled sled, String encryptedPassword, String key) {
		String decryptedPassword;
		if (StringUtils.isBlank(key)) {
			decryptedPassword = sled.decrypt(encryptedPassword);
		} else {
			decryptedPassword = sled.decrypt(encryptedPassword, key);
		}
		return decryptedPassword;
	}

	private String elaborateUrl(DatabaseProperties databaseProperties) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(databaseProperties.getUrl());

		addProperties(databaseProperties, stringBuilder);

		return stringBuilder.toString();
	}

	private void addProperties(DatabaseProperties databaseProperties, StringBuilder stringBuilder) {

		Optional<Map<String, String>> optionalOtherProperties = Optional
				.ofNullable(databaseProperties.getOtherConnectionProperties());

		if (optionalOtherProperties.isPresent()) {
			stringBuilder.append("?");
			for (Entry<String, String> otherPropertyEntry : optionalOtherProperties.get()
					.entrySet()) {
				stringBuilder.append(String.format(PARAMETER_FORMAT_TEMPLATE,
						otherPropertyEntry.getKey(), otherPropertyEntry.getValue()));

			}
		}
	}
}
