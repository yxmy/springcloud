package com.test.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloError")
    public String sayHello(String name){
        return this.restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }

    public String helloError(String name){
        return "hi," + name + ",sorry, error!";
    }
}
