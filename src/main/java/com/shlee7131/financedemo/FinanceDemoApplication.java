package com.shlee7131.financedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FinanceDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(FinanceDemoApplication.class, args);
	}
}
