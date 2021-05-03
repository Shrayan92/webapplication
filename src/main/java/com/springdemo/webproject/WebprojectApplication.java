package com.springdemo.webproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WebprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebprojectApplication.class, args);
	}

}
