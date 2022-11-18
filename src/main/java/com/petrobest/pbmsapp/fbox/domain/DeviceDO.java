package com.petrobest.pbmsapp.fbox.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@TableName("serv_box")
public class DeviceDO {
    /**
     * box_id
     */
    @TableId
    private String boxId;

    /**
     * 设备序列号
     */
    @TableField
    private String boxDeviceId;

    /**
     * 别名
     */
    @TableField
    private String boxName;

    /**
     * 状态
     */
    @TableField
    private String boxStat;

    /**
     * 地址
     */
    @TableField
    private String boxAddr;

    /**
     * 设备组
     */
    @TableField
    private String boxGroup;

    /**
     * 创建时间
     */
    @TableField
    private String createTime;

    /**
     * 修改时间
     */
    @TableField
    private String modifyTime;


    public static final String BOX_ID = "box_id";
    public static final String BOX_DEVICE_ID = "box_device_id";
    public static final String BOX_NAME = "box_name";
    public static final String BOX_STAT = "box_stat";
    public static final String BOX_ADDR = "box_addr";
    public static final String BOX_GROUP = "box_group";
}
