package com.commerce.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class AuthTokenInterceptor implements RequestInterceptor {

    @Resource
    private HttpServletRequest request;

    @Override
    public void apply(RequestTemplate template) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        template.header(HttpHeaders.AUTHORIZATION, token);
    }
}
