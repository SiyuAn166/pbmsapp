package com.petrobest.pbmsapp.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_dictionary_type")
public class DictionaryTypeDO implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 编码
     */
    @TableField
    private String code;

    /**
     * 状态
     */
    @TableField
    private Integer status;

    /**
     * 字典类型名称
     */
    @TableField
    private String text;


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
    public static final String CODE = "code";
    public static final String STATUS = "status";
    public static final String TEXT = "text";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";
}
