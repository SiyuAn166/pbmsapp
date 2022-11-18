package com.petrobest.pbmsapp.project.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("pro_project_device")
public class ProjectDeviceDO implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 项目号
     */
    @TableField
    private String proId;

    /**
     * 设备类型，关联数据字典
     */
    @TableField
    private String deviceType;

    /**
     * 设备序列号
     */
    @TableField
    private String deviceSerial;

    /**
     * 密码
     */
    @TableField
    private String devicePassword;

    /**
     * 调试账号
     */
    @TableField
    private String deviceDebugUser;

    /**
     * 调试密码
     */
    @TableField
    private String deviceDebugPassword;

    /**
     * 手机号
     */
    @TableField
    private String deviceMobile;

    /**
     * 开通时间
     */
    @TableField
    private Date deviceApplyTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;


    public static final String ID = "id";
    public static final String PRO_ID = "pro_id";
    public static final String DEVICE_TYPE = "device_type";
    public static final String DEVICE_SERIAL = "device_serial";
    public static final String DEVICE_PASSWORD = "device_password";
    public static final String DEVICE_DEBUG_USER = "device_debug_user";
    public static final String DEVICE_DEBUG_PASSWORD = "device_debug_password";
    public static final String DEVICE_MOBILE = "device_mobile";
    public static final String DEVICE_APPLY_TIME = "device_apply_time";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";
}
