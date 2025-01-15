package com.docker.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/hello")
    public String sayHello() {
    	System.out.println("Hi this Springggggggggbootttt");
        return "Hello Welcome to Rest Controller!";
    }
}