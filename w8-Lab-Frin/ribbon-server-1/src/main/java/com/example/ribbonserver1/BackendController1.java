package com.example.ribbonserver1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController1 {

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String health() {
        return "I am OK (server-1)";
    }

    @GetMapping("/backend")
    public String backend() {
        String port = environment.getProperty("local.server.port");
        return "Hello from Backend 1 :: Port " + port;
    }
}

