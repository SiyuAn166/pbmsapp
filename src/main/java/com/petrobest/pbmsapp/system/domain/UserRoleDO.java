package com.petrobest.pbmsapp.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user_role")
public class UserRoleDO implements Serializable {


    /**
     * 用户id
     */
    @TableId(type = IdType.INPUT)
    private String userId;

    /**
     * 角色id
     */
    @TableId(type = IdType.INPUT)
    private String roleId;



}
