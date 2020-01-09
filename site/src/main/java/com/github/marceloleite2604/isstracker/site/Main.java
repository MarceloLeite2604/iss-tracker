package com.github.marceloleite2604.isstracker.site;

import com.github.marceloleite2604.isstracker.site.properties.DatabaseProperties;
import com.github.marceloleite2604.isstracker.site.properties.EncryptionProperties;
import com.github.marceloleite2604.isstracker.site.properties.GoogleProperties;
import com.github.marceloleite2604.isstracker.site.properties.ProgramProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ ProgramProperties.class, DatabaseProperties.class,
		EncryptionProperties.class, GoogleProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
