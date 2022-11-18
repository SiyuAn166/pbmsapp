package com.petrobest.pbmsapp.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.swing.event.InternalFrameEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@TableName("sys_role")
public class RoleDO implements Serializable {
    /**
     * 角色id
     */
    @TableId
    private String roleId;

    /**
     * 角色名称
     */
    @TableField
    private String roleName;

    /**
     * 角色描述
     */
    @TableField
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date modifyTime;

    /**
     * 新用户默认选中角色 0否 1是
     */
    @TableField
    private Integer defaultChecked;

    @TableField(exist = false)
    private List<String> resourceIds;

    @TableField(exist = false)
    private List<ResourceDO> resources;


    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";
    public static final String REMARK = "remark";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";
    public static final String DEFAULT_CHECKED = "default_checked";
}
