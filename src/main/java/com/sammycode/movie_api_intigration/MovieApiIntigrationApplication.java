package com.sammycode.movie_api_intigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MovieApiIntigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiIntigrationApplication.class, args);
	}
}
