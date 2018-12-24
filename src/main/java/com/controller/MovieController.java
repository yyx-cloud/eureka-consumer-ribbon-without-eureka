package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieController {
//  @Autowired
//  private RestTemplate restTemplate;

  /*@GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://localhost:8000/" + id, User.class);
  }*/

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);


    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public Map findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://eureka-provider/" + id, Map.class);
    }

    @GetMapping("/logInstance")
    public void logInstance() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-provider");
        logger.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());

    }
}
