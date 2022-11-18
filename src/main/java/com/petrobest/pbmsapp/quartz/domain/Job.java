package com.petrobest.pbmsapp.quartz.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("qrtz_job")
public class Job implements Serializable {

    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL("0"),
        /**
         * 暂停
         */
        PAUSE("1");

        private String value;

        ScheduleStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @TableId
    private String jobId;
    @TableField
    private String beanName;
    @TableField
    private String methodName;
    @TableField
    private String params;
    @TableField
    private String cronExpression;
    @TableField
    private String status;
    @TableField
    private String remark;
    @TableField
    private Date createTime;
}
