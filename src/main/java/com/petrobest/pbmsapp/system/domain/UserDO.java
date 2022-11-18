package com.petrobest.pbmsapp.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class UserDO implements Serializable {
    /**
     * 账户状态
     */
    public static final int STATUS_VALID = 1;

    public static final int STATUS_LOCK = 0;


    /**
     * 性别
     */
    public static final String SEX_MALE = "0";

    public static final String SEX_FEMALE = "1";

    public static final String SEX_UNKNOW = "2";

    /**
     * 用户id
     */

    @TableId
    private String userId;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * 姓名
     */
    @TableField
    private String fullname;

    /**
     * 部门id
     */
    @TableField
    private Long deptId;

    /**
     * 邮箱
     */
    @TableField
    private String email;

    /**
     * 联系电话
     */
    @TableField
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
    @TableField
    private Integer status;

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

    /**
     * 最近访问时间
     */
    @TableField
    private Date lastLoginTime;

    /**
     * 性别 0男 1女
     */
    @TableField
    private String sex;

    /**
     * 主题
     */
    @TableField
    private String theme;

    /**
     * 头像
     */
    @TableField
    private String avatar;

    /**
     * 描述
     */
    @TableField
    private String description;

    @TableField(exist = false)
    private List<String> roleIds;

    @TableField(exist = false)
    private List<RoleDO> roles;

    //shiro
    public String getAuthCacheKey() {
        return getUserId();
    }


    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FULLNAME = "fullname";
    public static final String DEPT_ID = "dept_id";
    public static final String EMAIL = "email";
    public static final String MOBILE = "mobile";
    public static final String STATUS = "status";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";
    public static final String LAST_LOGIN_TIME = "last_login_time";
    public static final String SEX = "sex";
    public static final String THEME = "theme";
    public static final String AVATAR = "avatar";
    public static final String DESCRIPTION = "description";
}
