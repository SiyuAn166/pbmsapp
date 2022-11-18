package com.petrobest.pbmsapp.system.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_log")
public class LogDO implements Serializable {

    /**
     * id
     */
    @TableId
    private String logId;

    /**
     * 用户id
     */
    @TableField
    private String userId;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 用户操作
     */
    @TableField
    private String operation;

    /**
     * 响应时间
     */
    @TableField
    private Integer time;

    /**
     * 请求方法
     */
    @TableField
    private String method;

    /**
     * 请求参数
     */
    @TableField
    private String params;

    /**
     * ip地址
     */
    @TableField
    private String ip;

    /**
     * 创建时间
     */
    @TableField
    private Date createTime;


    public static final String LOG_ID = "log_id";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String OPERATION = "operation";
    public static final String TIME = "time";
    public static final String METHOD = "method";
    public static final String PARAMS = "params";
    public static final String IP = "ip";
    public static final String CREATE_TIME = "create_time";



}
