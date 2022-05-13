package com.commerce.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.commerce.common.auth.DefaultUserDetails;
import com.commerce.common.entity.CommonUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;
import java.util.Optional;

public class AuthUtil {

    public static OAuth2Authentication getOAuth2Authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            // 当token域为UsernamePasswordAuthenticationToken时，表明尚未登录
            return null;
        }
        return (OAuth2Authentication) auth;
    }

    public static CommonUser clientGetCurrentUser() {
        OAuth2Authentication auth = getOAuth2Authentication();
        if (auth == null) throw new RuntimeException("");
        Map<?,?> principal = (Map<?, ?>) Optional.of(auth).map(OAuth2Authentication::getUserAuthentication).map(Authentication::getDetails).map(Map.class::cast).orElseThrow(()->new RuntimeException("")).get("principal");
        return JSONObject.parseObject(JSONObject.toJSONString(principal), DefaultUserDetails.class).getUser();

    }

    public static CommonUser serverGetCurrentUser(){
        OAuth2Authentication auth = getOAuth2Authentication();
        if (auth == null) throw new RuntimeException("");
        return ((DefaultUserDetails) auth.getPrincipal()).getUser();
    }
}
