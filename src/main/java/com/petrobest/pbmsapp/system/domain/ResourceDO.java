package com.petrobest.pbmsapp.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("sys_resource")
public class ResourceDO implements Serializable, Comparable<ResourceDO> {

//    public static final long PARENTID_RESOURCE = -1; //非菜单资源parentId为-1
    //类型

    public static final String TOP_MENU = "0"; //顶级菜单pid=0
    public static final char TYPE_DICTIONARY = '0'; //目录
    public static final char TYPE_RESOURCE = '1';//菜单
    public static final char TYPE_BUTTON = '2';//按钮

    /**
     * 菜单/按钮id
     */
    @TableId
    private String resourceId;

    /**
     * 上级菜单id
     */
    @TableField
    private String parentId;

    /**
     * 菜单/按钮名称
     */
    @TableField
    private String resourceName;

    /**
     * 资源url
     */
    @TableField
    private String url;

    /**
     * 权限标识
     */
    @TableField
    private String perms;

    /**
     * 图标
     */
    @TableField
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @TableField
    private Character type;

    /**
     * 排序
     */
    @TableField
    private Integer orderNum;

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


    @TableField(exist = false)
    private List<ResourceDO> children;


    public static final String RESOURCE_ID = "resource_id";
    public static final String PARENT_ID = "parent_id";
    public static final String RESOURCE_NAME = "resource_name";
    public static final String URL = "url";
    public static final String PERMS = "perms";
    public static final String ICON = "icon";
    public static final String TYPE = "type";
    public static final String ORDER_NUM = "order_num";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";

    @Override
    public int compareTo(ResourceDO o) {
        return getOrderNum() - o.getOrderNum();
    }
}
