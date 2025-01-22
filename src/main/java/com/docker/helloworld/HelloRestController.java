package com.docker.helloworld;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/")
    public String sayHello() {
    	int a =10;
        return "Hello Welcome to Rest Controller!";
    }
    @PostMapping("/user")
    public ResponseEntity<String> createUser() {
        System.out.println("Post API");
        return ResponseEntity.ok("POST API Worked");
    }


}