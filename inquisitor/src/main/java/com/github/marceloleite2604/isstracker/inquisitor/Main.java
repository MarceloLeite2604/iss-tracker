package com.github.marceloleite2604.isstracker.inquisitor;

import com.github.marceloleite2604.isstracker.inquisitor.properties.DatabaseProperties;
import com.github.marceloleite2604.isstracker.inquisitor.properties.EncryptionProperties;
import com.github.marceloleite2604.isstracker.inquisitor.properties.ProgramProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({ ProgramProperties.class, DatabaseProperties.class, EncryptionProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
