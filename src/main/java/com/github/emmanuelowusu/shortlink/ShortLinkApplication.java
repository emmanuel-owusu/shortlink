package com.github.emmanuelowusu.shortlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The ShortLinkApplication class is the main Spring Boot application class for a simple URL-shortening service.
 *
 * @SpringBootApplication This annotation enables automatic configuration and component scanning for a Spring Boot application.
 */
@SpringBootApplication
public class ShortLinkApplication {

	/**
	 * The main method is the entry point for the Spring Boot application.
	 *
	 * @param args The command line arguments passed to the application.
	 * @throws Exception If any exceptions occur during application startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ShortLinkApplication.class, args);
	}

}
