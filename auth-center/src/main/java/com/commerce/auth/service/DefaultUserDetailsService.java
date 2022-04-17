package com.commerce.auth.service;

import com.commerce.common.entity.CommonUser;
import com.commerce.common.entity.enums.UserStatusEnum;
import com.commerce.common.service.CommonUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private CommonUserService commonUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CommonUser userPO = commonUserService.findByUsername(username);
        if(userPO == null || userPO.getStatus() != UserStatusEnum.Normal) throw new IllegalArgumentException("");
        return Optional.of(userPO).map(DefaultUserDetails::new).get();
    }

}