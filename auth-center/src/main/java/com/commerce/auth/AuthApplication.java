package com.commerce.auth;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.commerce.**")
@MapperScan(basePackages = "com.commerce.common.mapper")
public class AuthApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder(AuthApplication.class).run(args);
    }

}
