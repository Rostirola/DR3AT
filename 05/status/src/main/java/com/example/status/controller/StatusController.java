package com.example.status.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        System.out.println("Status");
        return ResponseEntity.ok("Service is active");
    }
}
