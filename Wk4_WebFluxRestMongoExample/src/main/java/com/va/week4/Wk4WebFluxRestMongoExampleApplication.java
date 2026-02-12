package com.va.week4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Wk4WebFluxRestMongoExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Wk4WebFluxRestMongoExampleApplication.class, args);
		
		System.out.println("Spring Boot Webflux App started");

	}

}
