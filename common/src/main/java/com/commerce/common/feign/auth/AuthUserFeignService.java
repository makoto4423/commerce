package com.commerce.common.feign.auth;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.commerce.common.entity.CommonUser;
import com.commerce.common.feign.auth.impl.AuthUserFeignFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *  使用fallback 就不能在类上使用{@link org.springframework.web.bind.annotation.RequestMapping} 否则会启动报错
 *
 *  测试了一下， 当一个类仅被{@link org.springframework.stereotype.Component}
 *  和 {@link org.springframework.web.bind.annotation.RequestMapping} 注释时，
 *  spring将会去拼装url放入上下文中（不加{@link org.springframework.stereotype.Controller}都能访问），
 *  不加{@link org.springframework.web.bind.annotation.RequestMapping}则不会放入上下文中，
 *  符合 fallback 的报错
 */
@FeignClient(value = "auth-center-service", fallback = AuthUserFeignFallbackService.class)
public interface AuthUserFeignService {

    @PostMapping("/common-user/current0")
    CommonUser currentUser(CommonUser user);

}
