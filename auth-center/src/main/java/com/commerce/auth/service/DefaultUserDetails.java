package com.commerce.auth.service;

import com.commerce.common.entity.CommonUser;
import com.commerce.common.entity.enums.UserStatusEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class DefaultUserDetails implements UserDetails {

    private CommonUser user;

    public DefaultUserDetails(CommonUser user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return checkUser();
    }

    @Override
    public boolean isAccountNonLocked() {
        return checkUser();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return checkUser();
    }

    @Override
    public boolean isEnabled() {
        return checkUser();
    }

    public boolean checkUser(){
        return user.getStatus() != null && user.getStatus() == UserStatusEnum.Normal;
    }
}
