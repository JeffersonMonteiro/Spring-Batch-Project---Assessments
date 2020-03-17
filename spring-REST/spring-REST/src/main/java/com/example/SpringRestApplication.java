package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"com.example"})

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//
//		return builder.defaultHeader("User-Agent", "Code Club").build();
//	}
}
