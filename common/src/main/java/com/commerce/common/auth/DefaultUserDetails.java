package com.commerce.common.auth;

import com.commerce.common.entity.CommonUser;
import com.commerce.common.entity.enums.UserStatusEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class DefaultUserDetails implements UserDetails {

    private CommonUser user;
    private List<Authority> authorities;

    public DefaultUserDetails(CommonUser user){
        this.user = user;
        // 此处用匿名类会导致jackson, gson序列化报错
        // nacos的依赖里依赖了fastjson, 那算了继续fastjson
        this.authorities = Collections.singletonList(new Authority(user.getType().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    private static class Authority implements GrantedAuthority{

        private final String authority;

        private Authority(String authority){
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return this.authority;
        }
    }
}
