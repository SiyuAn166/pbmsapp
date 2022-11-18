package com.petrobest.pbmsapp.project.domain;

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
@TableName("pro_project")
public class ProjectDO implements Serializable {
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
     * 项目名称
     */
    @TableField
    private String proName;

    /**
     * 项目经理
     */
    @TableField
    private String proMgr;

    /**
     * 客户名称
     */
    @TableField
    private String customerName;

    /**
     * 调试人员
     */
    @TableField
    private String proDebugger;

    /**
     * 图纸档案号
     */
    @TableField
    private String proDrawingId;

    /**
     * 设备编号
     */
    @TableField
    private String deviceId;

    /**
     * 设备名称
     */
    @TableField
    private String deviceName;

    /**
     * 设备描述
     */
    @TableField
    private String deviceComment;

    /**
     * 设计人
     */
    @TableField
    private String deviceDesigner;

    /**
     * 出厂日期
     */
    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deviceProduceDate;

    /**
     * 项目坐标-经度
     */
    @TableField
    private Double proLng;

    /**
     * 项目坐标-纬度
     */
    @TableField
    private Double proLat;

    /**
     * 场站地址
     */
    @TableField
    private String proAddr;

    /**
     * 省
     */
    @TableField
    private String proProvince;

    /**
     * 市
     */
    @TableField
    private String proCity;

    /**
     * 区
     */
    @TableField
    private String proDistrict;

    /**
     * 具体地址
     */
    @TableField
    private String proDetail;

    /**
     * 是否上传监控中心 1是0否
     */
    @TableField
    private Integer toMonitorCenter;

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
    private List<ProjectDeviceDO> projectDeviceDOs;

    public static final String ID = "id";
    public static final String PRO_ID = "pro_id";
    public static final String PRO_NAME = "pro_name";
    public static final String PRO_MGR = "pro_mgr";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String PRO_DEBUGGER = "pro_debugger";
    public static final String PRO_DRAWING_ID = "pro_drawing_id";
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_NAME = "device_name";
    public static final String DEVICE_COMMENT = "device_comment";
    public static final String DEVICE_DESIGNER = "device_designer";
    public static final String DEVICE_PRODUCE_DATE = "device_produce_date";
    public static final String PRO_ADDR = "pro_addr";
    public static final String TO_MONITOR_CENTER = "to_monitor_center";
    public static final String CREATE_TIME = "create_time";
    public static final String MODIFY_TIME = "modify_time";
}
