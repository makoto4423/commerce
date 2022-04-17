package com.commerce.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum UserStatusEnum {

    Normal(1), Lock(2), Ban(3);
    @EnumValue
    public final int val;

    UserStatusEnum(int value){
        val = value;
    }

}
