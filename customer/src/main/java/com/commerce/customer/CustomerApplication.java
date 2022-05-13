package com.commerce.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients("com.commerce.common.feign.auth")
@MapperScan(basePackages = "com.commerce.common.mapper")
@SpringBootApplication(scanBasePackages = "com.commerce.**")
public class CustomerApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder(CustomerApplication.class).run(args);
    }


}
