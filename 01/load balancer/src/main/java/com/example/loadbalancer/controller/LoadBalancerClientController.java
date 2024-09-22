package com.example.loadbalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoadBalancerClientController {

    private final RestTemplate restTemplate;

    public LoadBalancerClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/check-status")
    public String checkServiceStatus() {
        String status = restTemplate.getForObject("http://status/status", String.class);
        return "Status Service Response: " + status;
    }
}

