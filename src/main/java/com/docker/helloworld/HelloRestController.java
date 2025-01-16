package com.docker.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/hello")
    public String sayHello() {
    	int a =10;
    	System.out.println("TO Test CI CD Pipeline third commit");
        return "Hello Welcome to Rest Controller!";
    }
}