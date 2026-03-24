package com.example.ribbonserver2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController2 {

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String health() {
        return "I am OK (server-2)";
    }

    @GetMapping("/backend")
    public String backend() {
        String port = environment.getProperty("local.server.port");
        return "Hello from Backend 2 :: Port " + port;
    }
}

