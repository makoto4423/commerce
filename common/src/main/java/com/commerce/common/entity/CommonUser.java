package com.commerce.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.commerce.common.entity.enums.UserStatusEnum;
import com.commerce.common.entity.enums.UserTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CommonUser对象", description="")
public class CommonUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private String password;

    private String email;

    private UserTypeEnum type;

    private UserStatusEnum status;

    private String lastHostAddress;


}
