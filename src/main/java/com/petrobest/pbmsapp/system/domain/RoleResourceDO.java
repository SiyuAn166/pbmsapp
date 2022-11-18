package com.petrobest.pbmsapp.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_role_resource")
public class RoleResourceDO implements Serializable {

    /**
     * 角色id
     */
    @TableId
    private String roleId;

    /**
     * 菜单/按钮id
     */
    @TableId
    private String resourceId;


}
