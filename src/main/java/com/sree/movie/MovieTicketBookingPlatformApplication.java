package com.sree.movie;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement

@OpenAPIDefinition(
		info = @Info(
				title = "Movie Ticket Booking Service API",
				description = "REST API documentation for Movie Ticket Booking Application",
				version = "v1.0",
				contact = @Contact(
						name = "Rahul",
						email = "rahul@example.com",
						url = "https://github.com/rahul"
				),
				license = @License(
						name = "Apache License 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Movie Ticket Booking Service – Developer Guide",
				url = "https://www.example.com/movie-ticket-booking-docs"
		)
)
public class MovieTicketBookingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketBookingPlatformApplication.class, args);
	}

}
