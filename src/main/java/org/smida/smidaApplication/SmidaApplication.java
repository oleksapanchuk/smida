package org.smida.smidaApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Stock Market Infrastructure Development Agency REST API Documentation",
				description = "RESTful service for managing company data and reports for the Stock Market Infrastructure Development Agency of Ukraine",
				version = "v1",
				contact = @Contact(
						name = "Oleksandr Panchuk",
						email = "olexandrpan4uk@gmail.com",
						url = "https://www.linkedin.com/in/panchuk-oleksa/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Stock Market Infrastructure Development Agency REST API Documentation",
				url = "https://github.com/oleksapanchuk/smida"
		)
)
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
