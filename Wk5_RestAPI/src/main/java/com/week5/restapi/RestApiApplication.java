package com.week5.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

   private static final Logger log = LoggerFactory.getLogger(RestApiApplication.class);

   public static void main(String[] args) {
      SpringApplication.run(RestApiApplication.class, args);
   }

   @Bean
   public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
   }

   @Bean
   @Profile("!test")
   public CommandLineRunner run(CountryService countryService) {
      return args -> {
         Countries country = countryService.fetchCountry("US");
         log.info(country.toString());
      };
   }
}
