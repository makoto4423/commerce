package com.commerce.auth.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.commerce.common.entity.CommonUser;
import com.commerce.common.feign.auth.AuthUserFeignService;
import com.commerce.common.utils.AuthUtil;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements AuthUserFeignService {

    @Override
    @SentinelResource(value = "qps")
    public CommonUser currentUser(CommonUser user) {
        throw new RuntimeException("eee");
    }
}
