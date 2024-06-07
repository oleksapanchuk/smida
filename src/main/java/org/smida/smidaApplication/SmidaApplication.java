package org.smida.smidaApplication;

import com.github.cloudyrock.spring.v5.EnableMongock;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@EnableMongock
public class SmidaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmidaApplication.class, args);
    }

}
