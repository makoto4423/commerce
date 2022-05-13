package com.commerce.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserTypeEnum {

    Customer( 1), Businessman(2);

    @EnumValue
    public final int value;

    UserTypeEnum(int val){
        this.value = val;
    }

    @JsonValue
    public String getName(){
        return name();
    }
}
