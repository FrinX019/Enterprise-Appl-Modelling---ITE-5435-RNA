package com.example.ribbonserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RibbonServer2Application {

    public static void main(String[] args) {
        SpringApplication.run(RibbonServer2Application.class, args);
    }
}

