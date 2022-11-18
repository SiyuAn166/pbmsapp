package com.petrobest.pbmsapp.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_dictionary_item")
public class DictionaryItemDO implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 序号
     */
    @TableField
    private Integer sort;

    /**
     * 字典内容
     */
    @TableField
    private String text;

    /**
     * 值
     */
    @TableField
    private String value;

    /**
     * 类型id
     */
    @TableField
    private String typeId;

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


    @TableField(exist = false)
    private DictionaryTypeDO type;


    public static final String ID = "id";
    public static final String SORT = "sort";
    public static final String TEXT = "text";
    public static final String VALUE = "value";
    public static final String TYPE_ID = "type_id";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";
}
