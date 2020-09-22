package com.democrata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemocrataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemocrataApplication.class, args);
	}
}
