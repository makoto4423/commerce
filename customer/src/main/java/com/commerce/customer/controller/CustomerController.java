package com.commerce.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping
    public String auth(){
        return "auth";
    }

    @GetMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

}
