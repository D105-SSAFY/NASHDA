package com.ssafy.nashda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NashdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NashdaApplication.class, args);
	}

}
