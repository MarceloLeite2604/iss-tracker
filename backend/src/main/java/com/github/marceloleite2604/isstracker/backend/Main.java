package com.github.marceloleite2604.isstracker.backend;

import com.github.marceloleite2604.isstracker.backend.properties.DatabaseProperties;
import com.github.marceloleite2604.isstracker.backend.properties.EncryptionProperties;
import com.github.marceloleite2604.isstracker.backend.properties.ProgramProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties({ ProgramProperties.class, DatabaseProperties.class,
		EncryptionProperties.class })
@EntityScan(basePackages = Main.BASE_PACKAGE)
@ComponentScan(basePackages = Main.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = Main.BASE_PACKAGE)
public class Main {

	public static final String BASE_PACKAGE = "com.github.marceloleite2604.isstracker";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
