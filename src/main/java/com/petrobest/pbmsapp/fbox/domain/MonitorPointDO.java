package com.petrobest.pbmsapp.fbox.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("serv_monitorpoint")
public class MonitorPointDO {

    @TableId
    private String id;
    @TableField
    private String deviceId;//设备序列号


    @TableField
    @JSONField(name = "time") //JSON解析时的日期格式化
    private Date receiveTime; //数据接收日期/时间

    @TableField
    @JSONField(name = "Data")
    private String receiveData;//数据内容,json格式 [{监控点1：值1},{监控点2：值2}]

    @TableField
    private Date createTime;
}
