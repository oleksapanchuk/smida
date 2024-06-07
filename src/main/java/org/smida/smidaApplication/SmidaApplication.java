package org.smida.smidaApplication;

import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class SmidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmidaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReportDetailsRepository repository) {
		return args -> {
			ReportDetails reportDetails = new ReportDetails(
					UUID.fromString("44444444-4444-4444-4444-444444444444"),
					"Financial Data",
					"Comments"
			);
			repository.insert(reportDetails);
		};
	}
}
