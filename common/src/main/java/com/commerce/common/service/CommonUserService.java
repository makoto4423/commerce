package com.commerce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.commerce.common.entity.CommonUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-04-16
 */
public interface CommonUserService extends IService<CommonUser> {

    CommonUser findByUsername(String userName);

}
