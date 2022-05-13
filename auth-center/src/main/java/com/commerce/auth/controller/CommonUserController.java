package com.commerce.auth.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.commerce.common.entity.CommonUser;
import com.commerce.common.entity.enums.UserStatusEnum;
import com.commerce.common.service.CommonUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
@RequestMapping("/common-user")
public class CommonUserController {

    @Resource
    private CommonUserService commonUserService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("/current")
    public Principal current(Principal principal){
        return principal;
    }

    @PostMapping
    public void add(@RequestBody CommonUser commonUser){
        commonUser.setPassword(passwordEncoder.encode(commonUser.getPassword()));
        commonUser.setStatus(UserStatusEnum.Normal);
        commonUserService.saveOrUpdate(commonUser);
    }

    @GetMapping("/test")
    @SentinelResource(value = "qps")
    public void test(){

    }

}
