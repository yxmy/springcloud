package com.test.eurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@RestController
@EnableCircuitBreaker
public class EurekaClient2Application {

    @Value ("${server.port}")
    String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient2Application.class, args);
    }

    @RequestMapping(value = "/hi")
    @HystrixCommand(fallbackMethod = "errormethod")
    public String home(@RequestParam(value = "name") String name){
        return "hi " + name + ",I am from port：" + port;
    }

    public String errormethod(String name){
        return "sorry, " + name + " something error!";
    }
}
