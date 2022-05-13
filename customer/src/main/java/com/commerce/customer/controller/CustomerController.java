package com.commerce.customer.controller;

import com.commerce.common.entity.CommonUser;
import com.commerce.common.feign.auth.AuthUserFeignService;
import com.commerce.common.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private AuthUserFeignService authUserFeignService;

    @GetMapping
    @PreAuthorize("hasAuthority('Customer')")
    public String auth(){
        log.info("user "+AuthUtil.clientGetCurrentUser().getUsername() + " login");
        return "auth";
    }

    @GetMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }


    @GetMapping("/test")
    public CommonUser current(){
        CommonUser user = new CommonUser();
        user.setEmail("email");
        user = authUserFeignService.currentUser(user);
        log.error(user.getUsername());
        return authUserFeignService.currentUser(user);
    }
}
