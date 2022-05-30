package com.shlee7131.financedemo;

import com.shlee7131.financedemo.service.UserService;
import com.shlee7131.financedemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
