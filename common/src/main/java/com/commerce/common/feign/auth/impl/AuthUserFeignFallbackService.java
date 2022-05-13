package com.commerce.common.feign.auth.impl;

import com.commerce.common.entity.CommonUser;
import com.commerce.common.feign.auth.AuthUserFeignService;
import org.springframework.stereotype.Component;

@Component
public class AuthUserFeignFallbackService implements AuthUserFeignService {
    @Override
    public CommonUser currentUser(CommonUser user) {
        user.setUsername("unknown");
        return user;
    }
}
