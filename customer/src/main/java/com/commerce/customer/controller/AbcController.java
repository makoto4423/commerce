package com.commerce.customer.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequestMapping("/ddd")
public class AbcController {

    @GetMapping("/customer/test")
    public void test(){

    }

}
