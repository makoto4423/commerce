package com.commerce.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum UserTypeEnum {

    Customer(1), Businessman(2);

    @EnumValue
    public final int value;

    UserTypeEnum(int val){
        value = val;
    }
}
