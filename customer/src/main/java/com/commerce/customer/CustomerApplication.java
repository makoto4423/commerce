package com.commerce.customer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder(CustomerApplication.class).run(args);
    }


}
