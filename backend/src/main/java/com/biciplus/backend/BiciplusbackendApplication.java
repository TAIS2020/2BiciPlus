package com.biciplus.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BiciplusbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiciplusbackendApplication.class, args);
	}

}
