package com.commerce.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.common.entity.CommonUser;
import com.commerce.common.mapper.CommonUserMapper;
import com.commerce.common.service.CommonUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-04-16
 */
@Service
public class CommonUserServiceImpl extends ServiceImpl<CommonUserMapper, CommonUser> implements CommonUserService {

    @Override
    public CommonUser findByUsername(String username) {
        QueryWrapper<CommonUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return getOne(wrapper);
    }
}
