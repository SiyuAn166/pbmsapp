/*
 Navicat Premium Data Transfer

 Source Server         : 39.104.78.213
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : 39.104.78.213:3306
 Source Schema         : bestms

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : 65001

 Date: 21/06/2019 17:17:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('Scheduler', 'TASK_1139097116080611330', 'DEFAULT', '0/3 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_job
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job`;
CREATE TABLE `qrtz_job`  (
  `JOB_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BEAN_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `METHOD_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PARAMS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CRON_EXPRESSION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `REMARK` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`JOB_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_job
-- ----------------------------
INSERT INTO `qrtz_job` VALUES ('1139097116080611330', 'mDataPersistTask', 'executeTask', 'Topic/flexem/fbox/300218020188/system/MonitorData', '0/3 * * * * ?', '1', NULL, '2019-06-13 17:08:11');

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('Scheduler', 'TASK_1139097116080611330', 'DEFAULT', NULL, 'com.petrobest.pbmsapp.quartz.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720027636F6D2E706574726F626573742E70626D736170702E71756172747A2E646F6D61696E2E4A6F6223F8D51122F0D7EE0200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F62496471007E00094C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C000673746174757371007E000978707400106D44617461506572736973745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016B50182EA47874000D302F33202A202A202A202A203F7400133131333930393731313630383036313133333074000B657865637574655461736B740031546F7069632F666C6578656D2F66626F782F3330303231383032303138382F73797374656D2F4D6F6E69746F7244617461740000740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('Scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('Scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('Scheduler', 'iZmiu4e27z0xp4Z1561102011229', 1561108618165, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('Scheduler', 'TASK_1139097116080611330', 'DEFAULT', 'TASK_1139097116080611330', 'DEFAULT', NULL, 1560416892000, -1, 5, 'PAUSED', 'CRON', 1560416890000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720027636F6D2E706574726F626573742E70626D736170702E71756172747A2E646F6D61696E2E4A6F6223F8D51122F0D7EE0200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F62496471007E00094C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C000673746174757371007E000978707400106D44617461506572736973745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016B501830787874000D302F33202A202A202A202A203F7400133131333930393731313630383036313133333074000B657865637574655461736B740031546F7069632F666C6578656D2F66626F782F3330303231383032303138382F73797374656D2F4D6F6E69746F724461746170740001317800);

-- ----------------------------
-- Table structure for serv_box
-- ----------------------------
DROP TABLE IF EXISTS `serv_box`;
CREATE TABLE `serv_box`  (
  `box_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `box_device_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备序列号',
  `box_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '别名',
  `box_stat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  `box_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `box_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备组',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`box_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of serv_box
-- ----------------------------
INSERT INTO `serv_box` VALUES ('1141585498522271745', 'ssssssssss', 'ssssssssss', 'sssssssss', 'sssssssssssssss', 'sssssssssss', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10, 0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT 0 COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`name`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 142 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件上传' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1139092189421772802', '123', 'admin1', '新增资源', 55, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415715858,\"icon\":\"\",\"parentId\":\"0\",\"perms\":\"system\",\"resourceId\":\"1139092189140754434\",\"resourceName\":\"系统管理\",\"type\":\"0\",\"url\":\"/system\"}]', '192.168.50.69', '2019-06-13 16:48:36');
INSERT INTO `sys_log` VALUES ('1139092256908124161', '123', 'admin1', '新增资源', 7, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415732008,\"icon\":\"\",\"parentId\":\"1139092189140754434\",\"perms\":\"system:user\",\"resourceId\":\"1139092256874569729\",\"resourceName\":\"用户管理\",\"type\":\"1\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-13 16:48:52');
INSERT INTO `sys_log` VALUES ('1139092311295664130', '123', 'admin1', '新增资源', 9, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415744974,\"icon\":\"\",\"parentId\":\"1139092189140754434\",\"perms\":\"system:role\",\"resourceId\":\"1139092311266304002\",\"resourceName\":\"角色管理\",\"type\":\"1\",\"url\":\"/system/role\"}]', '192.168.50.69', '2019-06-13 16:49:05');
INSERT INTO `sys_log` VALUES ('1139092372318593026', '123', 'admin1', '新增资源', 7, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415759523,\"icon\":\"\",\"parentId\":\"1139092189140754434\",\"perms\":\"system:resource\",\"resourceId\":\"1139092372289232898\",\"resourceName\":\"资源管理\",\"type\":\"1\",\"url\":\"/system/resource\"}]', '192.168.50.69', '2019-06-13 16:49:20');
INSERT INTO `sys_log` VALUES ('1139092439976910850', '123', 'admin1', '新增资源', 5, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415775657,\"icon\":\"\",\"parentId\":\"1139092189140754434\",\"perms\":\"system:job\",\"resourceId\":\"1139092439960133633\",\"resourceName\":\"定时任务\",\"type\":\"1\",\"url\":\"/system/job\"}]', '192.168.50.69', '2019-06-13 16:49:36');
INSERT INTO `sys_log` VALUES ('1139092496545488897', '123', 'admin1', '新增资源', 8, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415789139,\"icon\":\"\",\"parentId\":\"1139092189140754434\",\"perms\":\"system:log\",\"resourceId\":\"1139092496503545857\",\"resourceName\":\"日志管理\",\"type\":\"1\",\"url\":\"/system/log\"}]', '192.168.50.69', '2019-06-13 16:49:49');
INSERT INTO `sys_log` VALUES ('1139092591512920066', '123', 'admin1', '新增角色', 24, 'com.petrobest.pbmsapp.system.controller.RoleController.save()', '[{\"createTime\":1560415811763,\"defaultChecked\":0,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139092311266304002\",\"1139092372289232898\",\"1139092439960133633\",\"1139092496503545857\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '192.168.50.69', '2019-06-13 16:50:12');
INSERT INTO `sys_log` VALUES ('1139092661624905729', '123', 'admin1', '新增用户', 21, 'com.petrobest.pbmsapp.system.controller.UserController.save()', '[{\"authCacheKey\":\"1139092661528436737\",\"createTime\":1560415828484,\"fullname\":\"管理员\",\"password\":\"675aaf0e6a0ef7f1f4e7e97d23395ca3\",\"roleIds\":[\"1139092591391285250\"],\"status\":1,\"userId\":\"1139092661528436737\",\"username\":\"ansiyu\"}]', '192.168.50.69', '2019-06-13 16:50:29');
INSERT INTO `sys_log` VALUES ('1139093140748640257', '1139092661528436737', 'ansiyu', '新增资源', 5, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415942734,\"icon\":\"\",\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:save\",\"resourceId\":\"1139093140731863042\",\"resourceName\":\"新建用户\",\"type\":\"2\",\"url\":\"/system/user/save\"}]', '192.168.50.69', '2019-06-13 16:52:23');
INSERT INTO `sys_log` VALUES ('1139093272533671938', '1139092661528436737', 'ansiyu', '新增资源', 7, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415974152,\"icon\":\"\",\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:edit\",\"resourceId\":\"1139093272508506113\",\"resourceName\":\"编辑用户\",\"type\":\"2\",\"url\":\"/system/user/edit\"}]', '192.168.50.69', '2019-06-13 16:52:54');
INSERT INTO `sys_log` VALUES ('1139093350220570626', '1139092661528436737', 'ansiyu', '新增资源', 7, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560415992674,\"icon\":\"\",\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:delete\",\"resourceId\":\"1139093350195404801\",\"resourceName\":\"删除用户\",\"type\":\"2\",\"url\":\"/system/user/delete\"}]', '192.168.50.69', '2019-06-13 16:53:13');
INSERT INTO `sys_log` VALUES ('1139093416310218754', '1139092661528436737', 'ansiyu', '新增资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416008432,\"icon\":\"\",\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:batchDelete\",\"resourceId\":\"1139093416289247233\",\"resourceName\":\"删除用户\",\"type\":\"2\",\"url\":\"/system/user/batchDelete\"}]', '192.168.50.69', '2019-06-13 16:53:28');
INSERT INTO `sys_log` VALUES ('1139094165438078978', '1139092661528436737', 'ansiyu', '修改角色', 20, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":0,\"modifyTime\":1560416187025,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1139092311266304002\",\"1139092372289232898\",\"1139092439960133633\",\"1139092496503545857\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '192.168.50.69', '2019-06-13 16:56:27');
INSERT INTO `sys_log` VALUES ('1139094532368375810', '1139092661528436737', 'ansiyu', '新增资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416274520,\"icon\":\"\",\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:save\",\"resourceId\":\"1139094532343209986\",\"resourceName\":\"新建角色\",\"type\":\"2\",\"url\":\"/system/role/save\"}]', '192.168.50.69', '2019-06-13 16:57:55');
INSERT INTO `sys_log` VALUES ('1139094593504550914', '1139092661528436737', 'ansiyu', '新增资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416289096,\"icon\":\"\",\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:edit\",\"resourceId\":\"1139094593479385089\",\"resourceName\":\"编辑角色\",\"type\":\"2\",\"url\":\"/system/role/edit\"}]', '192.168.50.69', '2019-06-13 16:58:09');
INSERT INTO `sys_log` VALUES ('1139094687649898498', '1139092661528436737', 'ansiyu', '新增资源', 4, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416311544,\"icon\":\"\",\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:delete\",\"resourceId\":\"1139094687633121282\",\"resourceName\":\"删除角色\",\"type\":\"2\",\"url\":\"/system/role/delete\"}]', '192.168.50.69', '2019-06-13 16:58:32');
INSERT INTO `sys_log` VALUES ('1139094762094600193', '1139092661528436737', 'ansiyu', '新增资源', 7, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416329289,\"icon\":\"\",\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:batchDelete\",\"resourceId\":\"1139094762056851457\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/role/batchDelete\"}]', '192.168.50.69', '2019-06-13 16:58:49');
INSERT INTO `sys_log` VALUES ('1139094877618315266', '1139092661528436737', 'ansiyu', '新增资源', 5, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416356834,\"icon\":\"\",\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:save\",\"resourceId\":\"1139094877593149442\",\"resourceName\":\"新建资源\",\"type\":\"2\",\"url\":\"/system/resource/save\"}]', '192.168.50.69', '2019-06-13 16:59:17');
INSERT INTO `sys_log` VALUES ('1139094933784240129', '1139092661528436737', 'ansiyu', '新增资源', 5, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416370226,\"icon\":\"\",\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:edit\",\"resourceId\":\"1139094933759074305\",\"resourceName\":\"编辑资源\",\"type\":\"2\",\"url\":\"/system/resource/edit\"}]', '192.168.50.69', '2019-06-13 16:59:30');
INSERT INTO `sys_log` VALUES ('1139094988763176962', '1139092661528436737', 'ansiyu', '新增资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416383334,\"icon\":\"\",\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:delete\",\"resourceId\":\"1139094988742205442\",\"resourceName\":\"删除资源\",\"type\":\"2\",\"url\":\"/system/resource/delete\"}]', '192.168.50.69', '2019-06-13 16:59:43');
INSERT INTO `sys_log` VALUES ('1139095068970852354', '1139092661528436737', 'ansiyu', '新增资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416402457,\"icon\":\"\",\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:batchDelete\",\"resourceId\":\"1139095068949880833\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/resource/batchDelete\"}]', '192.168.50.69', '2019-06-13 17:00:02');
INSERT INTO `sys_log` VALUES ('1139095160842887169', '1139092661528436737', 'ansiyu', '修改角色', 22, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":0,\"modifyTime\":1560416424345,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1139092311266304002\",\"1139094532343209986\",\"1139094593479385089\",\"1139094687633121282\",\"1139094762056851457\",\"1139092372289232898\",\"1139094877593149442\",\"1139094933759074305\",\"1139094988742205442\",\"1139095068949880833\",\"1139092439960133633\",\"1139092496503545857\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '192.168.50.69', '2019-06-13 17:00:24');
INSERT INTO `sys_log` VALUES ('1139095706186293249', '1139092661528436737', 'ansiyu', '修改资源', 8, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1560416554380,\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:batchDelete\",\"resourceId\":\"1139093416289247233\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/user/batchDelete\"}]', '192.168.50.69', '2019-06-13 17:02:34');
INSERT INTO `sys_log` VALUES ('1139096052690329601', '1139092661528436737', 'ansiyu', '新增资源', 5, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416636994,\"icon\":\"\",\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:save\",\"resourceId\":\"1139096052665163777\",\"resourceName\":\"新建任务\",\"type\":\"2\",\"url\":\"/system/job/save\"}]', '192.168.50.69', '2019-06-13 17:03:57');
INSERT INTO `sys_log` VALUES ('1139096152938389505', '1139092661528436737', 'ansiyu', '新增资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416660894,\"icon\":\"\",\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:edit\",\"resourceId\":\"1139096152913223682\",\"resourceName\":\"编辑任务\",\"type\":\"2\",\"url\":\"/system/job/edit\"}]', '192.168.50.69', '2019-06-13 17:04:21');
INSERT INTO `sys_log` VALUES ('1139096232634359810', '1139092661528436737', 'ansiyu', '新增资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416679895,\"icon\":\"\",\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:delete\",\"resourceId\":\"1139096232609193986\",\"resourceName\":\"删除任务\",\"type\":\"2\",\"url\":\"/system/job/delete\"}]', '192.168.50.69', '2019-06-13 17:04:40');
INSERT INTO `sys_log` VALUES ('1139096304730251266', '1139092661528436737', 'ansiyu', '新增资源', 5, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560416697086,\"icon\":\"\",\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:batchDelete\",\"resourceId\":\"1139096304713474050\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/job/batchDelete\"}]', '192.168.50.69', '2019-06-13 17:04:57');
INSERT INTO `sys_log` VALUES ('1139096338150465537', '1139092661528436737', 'ansiyu', '修改角色', 19, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":0,\"modifyTime\":1560416705039,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1139092311266304002\",\"1139094532343209986\",\"1139094593479385089\",\"1139094687633121282\",\"1139094762056851457\",\"1139092372289232898\",\"1139094877593149442\",\"1139094933759074305\",\"1139094988742205442\",\"1139095068949880833\",\"1139092439960133633\",\"1139096052665163777\",\"1139096152913223682\",\"1139096232609193986\",\"1139096304713474050\",\"1139092496503545857\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '192.168.50.69', '2019-06-13 17:05:05');
INSERT INTO `sys_log` VALUES ('1139097116319686658', '1139092661528436737', 'ansiyu', '新增任务', 58, 'com.petrobest.pbmsapp.quartz.controller.JobController.addJob()', '[{\"beanName\":\"mDataPersistTask\",\"createTime\":1560416890532,\"cronExpression\":\"0/3 * * * * ?\",\"jobId\":\"1139097116080611330\",\"methodName\":\"executeTask\",\"params\":\"Topic/flexem/fbox/300218020188/system/MonitorData\",\"remark\":\"\",\"status\":\"1\"}]', '192.168.50.69', '2019-06-13 17:08:11');
INSERT INTO `sys_log` VALUES ('1139097560035106818', '1139092661528436737', 'ansiyu', '删除用户', 9, 'com.petrobest.pbmsapp.system.controller.UserController.delete()', '[{\"authCacheKey\":\"123\",\"userId\":\"123\"}]', '192.168.50.69', '2019-06-13 17:09:56');
INSERT INTO `sys_log` VALUES ('1139097618293989378', '1139092661528436737', 'ansiyu', '新增用户', 15, 'com.petrobest.pbmsapp.system.controller.UserController.save()', '[{\"authCacheKey\":\"1139097618239463425\",\"createTime\":1560417010255,\"fullname\":\"管理员\",\"password\":\"9b4c57854fecfd9cc56cb37207f33cf1\",\"roleIds\":[\"1139092591391285250\"],\"status\":1,\"userId\":\"1139097618239463425\",\"username\":\"admin1\"}]', '192.168.50.69', '2019-06-13 17:10:10');
INSERT INTO `sys_log` VALUES ('1139097918346145794', '1139097618239463425', 'admin1', '登录系统', 42, 'com.petrobest.pbmsapp.system.controller.LoginController.login()', '[{\"lastLoginTime\":1560417081671,\"modifyTime\":1560417081677,\"username\":\"admin1\"}]', '192.168.50.69', '2019-06-13 17:11:22');
INSERT INTO `sys_log` VALUES ('1139098007751929857', '1139097618239463425', 'admin1', '登录系统', 19, 'com.petrobest.pbmsapp.system.controller.LoginController.login()', '[{\"lastLoginTime\":1560417103107,\"modifyTime\":1560417103113,\"username\":\"admin1\"}]', '192.168.50.69', '2019-06-13 17:11:43');
INSERT INTO `sys_log` VALUES ('1139098192011898882', '1139097618239463425', 'admin1', '恢复任务', 43, 'com.petrobest.pbmsapp.quartz.controller.JobController.resumeJob()', '[{\"jobIds\":[\"1139097116080611330\"]}]', '192.168.50.69', '2019-06-13 17:12:27');
INSERT INTO `sys_log` VALUES ('1139098385688080385', '1139097618239463425', 'admin1', '暂停任务', 14, 'com.petrobest.pbmsapp.quartz.controller.JobController.pauseJob()', '[{\"jobIds\":[\"1139097116080611330\"]}]', '192.168.50.69', '2019-06-13 17:13:13');
INSERT INTO `sys_log` VALUES ('1139098842456174594', '1139097618239463425', 'admin1', '修改资源', 9, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-s-platform\",\"modifyTime\":1560417302120,\"parentId\":\"0\",\"perms\":\"system\",\"resourceId\":\"1139092189140754434\",\"resourceName\":\"系统管理\",\"type\":\"0\",\"url\":\"/system\"}]', '192.168.50.69', '2019-06-13 17:15:02');
INSERT INTO `sys_log` VALUES ('1139098869844979714', '1139097618239463425', 'admin1', '修改资源', 10, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-user-solid\",\"modifyTime\":1560417308654,\"parentId\":\"1139092189140754434\",\"perms\":\"system:user\",\"resourceId\":\"1139092256874569729\",\"resourceName\":\"用户管理\",\"type\":\"1\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-13 17:15:09');
INSERT INTO `sys_log` VALUES ('1139098928305188866', '1139097618239463425', 'admin1', '修改资源', 9, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-user\",\"modifyTime\":1560417322593,\"parentId\":\"1139092189140754434\",\"perms\":\"system:role\",\"resourceId\":\"1139092311266304002\",\"resourceName\":\"角色管理\",\"type\":\"1\",\"url\":\"/system/role\"}]', '192.168.50.69', '2019-06-13 17:15:23');
INSERT INTO `sys_log` VALUES ('1139099106210787330', '1139097618239463425', 'admin1', '修改资源', 9, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-folder-opened\",\"modifyTime\":1560417365006,\"parentId\":\"1139092189140754434\",\"perms\":\"system:resource\",\"resourceId\":\"1139092372289232898\",\"resourceName\":\"资源管理\",\"type\":\"1\",\"url\":\"/system/resource\"}]', '192.168.50.69', '2019-06-13 17:16:05');
INSERT INTO `sys_log` VALUES ('1139099157700063234', '1139097618239463425', 'admin1', '修改资源', 7, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-tickets\",\"modifyTime\":1560417377283,\"parentId\":\"1139092189140754434\",\"perms\":\"system:job\",\"resourceId\":\"1139092439960133633\",\"resourceName\":\"定时任务\",\"type\":\"1\",\"url\":\"/system/job\"}]', '192.168.50.69', '2019-06-13 17:16:17');
INSERT INTO `sys_log` VALUES ('1139099191216746497', '1139097618239463425', 'admin1', '修改资源', 6, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-document-copy\",\"modifyTime\":1560417385278,\"parentId\":\"1139092189140754434\",\"perms\":\"system:log\",\"resourceId\":\"1139092496503545857\",\"resourceName\":\"日志管理\",\"type\":\"1\",\"url\":\"/system/log\"}]', '192.168.50.69', '2019-06-13 17:16:25');
INSERT INTO `sys_log` VALUES ('1139099434511544321', '1139097618239463425', 'admin1', '新增角色', 31, 'com.petrobest.pbmsapp.system.controller.RoleController.save()', '[{\"createTime\":1560417443254,\"defaultChecked\":1,\"remark\":\"\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1139092311266304002\",\"1139094532343209986\",\"1139094593479385089\",\"1139094687633121282\",\"1139094762056851457\",\"1139092372289232898\",\"1139094877593149442\",\"1139094933759074305\",\"1139094988742205442\",\"1139095068949880833\",\"1139092439960133633\",\"1139096052665163777\",\"1139096152913223682\",\"1139096232609193986\",\"1139096304713474050\",\"1139092496503545857\"],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-13 17:17:23');
INSERT INTO `sys_log` VALUES ('1139099525557301250', '1139097618239463425', 'admin1', '修改角色', 19, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560417464978,\"remark\":\"ordinary\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139092311266304002\",\"1139092372289232898\",\"1139092439960133633\"],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-13 17:17:45');
INSERT INTO `sys_log` VALUES ('1140546155218944001', '1139097618239463425', 'admin1', '修改角色', 15, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":0,\"modifyTime\":1560762368212,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1139092311266304002\",\"1139094532343209986\",\"1139094593479385089\",\"1139094687633121282\",\"1139094762056851457\",\"1139092372289232898\",\"1139094877593149442\",\"1139094933759074305\",\"1139094988742205442\",\"1139095068949880833\",\"1139092439960133633\",\"1139096052665163777\",\"1139096152913223682\",\"1139096232609193986\",\"1139096304713474050\",\"1139092496503545857\",\"1140436352033628161\",\"1140436979409231874\",\"1140447865918201857\",\"1140525067779198978\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '127.0.0.1', '2019-06-17 17:06:08');
INSERT INTO `sys_log` VALUES ('1141151192545067009', '1139097618239463425', 'admin1', '新增资源', 152, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560906620402,\"icon\":\"\",\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:view\",\"resourceId\":\"1141151192029167617\",\"resourceName\":\"用户管理\",\"type\":\"1\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-19 09:10:21');
INSERT INTO `sys_log` VALUES ('1141151242344038401', '1139097618239463425', 'admin1', '修改资源', 116, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1560906632283,\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:view\",\"resourceId\":\"1141151192029167617\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-19 09:10:32');
INSERT INTO `sys_log` VALUES ('1141151361416134657', '1139097618239463425', 'admin1', '新增资源', 70, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560906660714,\"icon\":\"\",\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:view\",\"resourceId\":\"1141151361114144769\",\"resourceName\":\"用户管理\",\"type\":\"1\",\"url\":\"/system/role\"}]', '192.168.50.69', '2019-06-19 09:11:01');
INSERT INTO `sys_log` VALUES ('1141151395092201473', '1139097618239463425', 'admin1', '修改资源', 78, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1560906668736,\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:view\",\"resourceId\":\"1141151361114144769\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/role\"}]', '192.168.50.69', '2019-06-19 09:11:09');
INSERT INTO `sys_log` VALUES ('1141151488147030017', '1139097618239463425', 'admin1', '新增资源', 48, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560906690954,\"icon\":\"\",\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:view\",\"resourceId\":\"1141151487954092033\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/resource\"}]', '192.168.50.69', '2019-06-19 09:11:31');
INSERT INTO `sys_log` VALUES ('1141151578802716673', '1139097618239463425', 'admin1', '新增资源', 92, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560906712521,\"icon\":\"\",\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:view\",\"resourceId\":\"1141151578412646402\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/job\"}]', '192.168.50.69', '2019-06-19 09:11:53');
INSERT INTO `sys_log` VALUES ('1141151647329255426', '1139097618239463425', 'admin1', '新增资源', 66, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560906728886,\"icon\":\"\",\"parentId\":\"1139092496503545857\",\"perms\":\"system:log:view\",\"resourceId\":\"1141151647048237057\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/log\"}]', '192.168.50.69', '2019-06-19 09:12:09');
INSERT INTO `sys_log` VALUES ('1141156833418379265', '1139097618239463425', 'admin1', '新增资源', 67, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560907965345,\"icon\":\"\",\"parentId\":\"1140436979409231874\",\"perms\":\"monitorCenter:map:view\",\"resourceId\":\"1141156833133166594\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/log\"}]', '192.168.50.69', '2019-06-19 09:32:45');
INSERT INTO `sys_log` VALUES ('1141156898207793154', '1139097618239463425', 'admin1', '新增资源', 69, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1560907980790,\"icon\":\"\",\"parentId\":\"1140525067779198978\",\"perms\":\"deviceCenter:device:view\",\"resourceId\":\"1141156897918386178\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/log\"}]', '192.168.50.69', '2019-06-19 09:33:01');
INSERT INTO `sys_log` VALUES ('1141158230675259394', '1139097618239463425', 'admin1', '修改角色', 1310, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":0,\"modifyTime\":1560908297231,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1141151192029167617\",\"1139092311266304002\",\"1139094532343209986\",\"1139094593479385089\",\"1139094687633121282\",\"1139094762056851457\",\"1141151361114144769\",\"1139092372289232898\",\"1139094877593149442\",\"1139094933759074305\",\"1139094988742205442\",\"1139095068949880833\",\"1141151487954092033\",\"1139092439960133633\",\"1139096052665163777\",\"1139096152913223682\",\"1139096232609193986\",\"1139096304713474050\",\"1141151578412646402\",\"1139092496503545857\",\"1141151647048237057\",\"1140436352033628161\",\"1140436979409231874\",\"1141156833133166594\",\"1140447865918201857\",\"1140525067779198978\",\"1141156897918386178\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '192.168.50.69', '2019-06-19 09:38:19');
INSERT INTO `sys_log` VALUES ('1141158385658986497', '1139097618239463425', 'admin1', '修改资源', 80, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-data-analysis\",\"modifyTime\":1560908335414,\"parentId\":\"1140436352033628161\",\"perms\":\"monitorCenter:map\",\"resourceId\":\"1140436979409231874\",\"resourceName\":\"地图监控\",\"type\":\"1\",\"url\":\"/monitorCenter/map\"}]', '192.168.50.69', '2019-06-19 09:38:55');
INSERT INTO `sys_log` VALUES ('1141158408928985089', '1139097618239463425', 'admin1', '修改资源', 62, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-monitor\",\"modifyTime\":1560908340982,\"parentId\":\"1140447865918201857\",\"perms\":\"deviceCenter:device\",\"resourceId\":\"1140525067779198978\",\"resourceName\":\"设备管理\",\"type\":\"1\",\"url\":\"/deviceCenter/device\"}]', '192.168.50.69', '2019-06-19 09:39:01');
INSERT INTO `sys_log` VALUES ('1141158432593248258', '1139097618239463425', 'admin1', '修改资源', 82, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-user-solid\",\"modifyTime\":1560908346605,\"parentId\":\"1139092189140754434\",\"perms\":\"system:user\",\"resourceId\":\"1139092256874569729\",\"resourceName\":\"用户管理\",\"type\":\"1\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-19 09:39:07');
INSERT INTO `sys_log` VALUES ('1141158450330959873', '1139097618239463425', 'admin1', '修改资源', 62, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-user\",\"modifyTime\":1560908350853,\"parentId\":\"1139092189140754434\",\"perms\":\"system:role\",\"resourceId\":\"1139092311266304002\",\"resourceName\":\"角色管理\",\"type\":\"1\",\"url\":\"/system/role\"}]', '192.168.50.69', '2019-06-19 09:39:11');
INSERT INTO `sys_log` VALUES ('1141158466583887874', '1139097618239463425', 'admin1', '修改资源', 65, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-folder-opened\",\"modifyTime\":1560908354724,\"parentId\":\"1139092189140754434\",\"perms\":\"system:resource\",\"resourceId\":\"1139092372289232898\",\"resourceName\":\"资源管理\",\"type\":\"1\",\"url\":\"/system/resource\"}]', '192.168.50.69', '2019-06-19 09:39:15');
INSERT INTO `sys_log` VALUES ('1141158484342571009', '1139097618239463425', 'admin1', '修改资源', 40, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-tickets\",\"modifyTime\":1560908358983,\"parentId\":\"1139092189140754434\",\"perms\":\"system:job\",\"resourceId\":\"1139092439960133633\",\"resourceName\":\"定时任务\",\"type\":\"1\",\"url\":\"/system/job\"}]', '192.168.50.69', '2019-06-19 09:39:19');
INSERT INTO `sys_log` VALUES ('1141158500121542658', '1139097618239463425', 'admin1', '修改资源', 58, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-document-copy\",\"modifyTime\":1560908362727,\"parentId\":\"1139092189140754434\",\"perms\":\"system:log\",\"resourceId\":\"1139092496503545857\",\"resourceName\":\"日志管理\",\"type\":\"1\",\"url\":\"/system/log\"}]', '192.168.50.69', '2019-06-19 09:39:23');
INSERT INTO `sys_log` VALUES ('1141158730674044930', '1139097618239463425', 'admin1', '修改角色', 372, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908417381,\"remark\":\"ordinary\",\"resourceIds\":[\"1141151192029167617\",\"1141151361114144769\",\"1141151487954092033\",\"1141151578412646402\",\"1139092496503545857\",\"1141151647048237057\"],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:40:18');
INSERT INTO `sys_log` VALUES ('1141158755101671425', '1139097618239463425', 'admin1', '修改角色', 331, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908423246,\"remark\":\"ordinary\",\"resourceIds\":[\"1141151192029167617\",\"1141151361114144769\",\"1141151487954092033\",\"1141151578412646402\"],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:40:24');
INSERT INTO `sys_log` VALUES ('1141158823556907010', '1139097618239463425', 'admin1', '新增用户', 400, 'com.petrobest.pbmsapp.system.controller.UserController.save()', '[{\"authCacheKey\":\"1141158822256672769\",\"createTime\":1560908439588,\"fullname\":\"测试\",\"password\":\"bd98d816b3b3dc61ff6686b642196b9d\",\"roleIds\":[\"1139099434368937985\"],\"status\":1,\"userId\":\"1141158822256672769\",\"username\":\"testuser\"}]', '192.168.50.69', '2019-06-19 09:40:40');
INSERT INTO `sys_log` VALUES ('1141160275578814466', '1139097618239463425', 'admin1', '修改角色', 274, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908785813,\"remark\":\"ordinary\",\"resourceIds\":[\"1141151192029167617\"],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:46:26');
INSERT INTO `sys_log` VALUES ('1141160410014646273', '1139097618239463425', 'admin1', '修改角色', 166, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908817972,\"remark\":\"ordinary\",\"resourceIds\":[],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:46:58');
INSERT INTO `sys_log` VALUES ('1141160519167213569', '1139097618239463425', 'admin1', '修改角色', 166, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908843997,\"remark\":\"ordinary\",\"resourceIds\":[],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:47:24');
INSERT INTO `sys_log` VALUES ('1141160646904741889', '1139097618239463425', 'admin1', '修改角色', 164, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908874454,\"remark\":\"ordinary\",\"resourceIds\":[],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:47:55');
INSERT INTO `sys_log` VALUES ('1141160670334124033', '1139097618239463425', 'admin1', '修改角色', 161, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908880043,\"remark\":\"ordinary\",\"resourceIds\":[],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:48:00');
INSERT INTO `sys_log` VALUES ('1141160831282151425', '1139097618239463425', 'admin1', '修改角色', 164, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560908918414,\"remark\":\"ordinary\",\"resourceIds\":[],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:48:39');
INSERT INTO `sys_log` VALUES ('1141162122536398850', '1139097618239463425', 'admin1', '修改角色', 140, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560909226181,\"remark\":\"ordinary\",\"resourceIds\":[],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:53:46');
INSERT INTO `sys_log` VALUES ('1141163665155260417', '1139097618239463425', 'admin1', '修改角色', 237, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560909593853,\"remark\":\"ordinary\",\"resourceIds\":[],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 09:59:54');
INSERT INTO `sys_log` VALUES ('1141172301936062466', '1139097618239463425', 'admin1', '修改角色', 287, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560911652991,\"remark\":\"ordinary\",\"resourceIds\":[\"1141151192029167617\"],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 10:34:13');
INSERT INTO `sys_log` VALUES ('1141173680947720194', '1139097618239463425', 'admin1', '修改角色', 353, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":1,\"modifyTime\":1560911981823,\"remark\":\"ordinary\",\"resourceIds\":[\"1140436352033628161\",\"1140436979409231874\",\"1141156833133166594\",\"1140447865918201857\",\"1140525067779198978\",\"1141156897918386178\"],\"roleId\":\"1139099434368937985\",\"roleName\":\"普通用户\"}]', '192.168.50.69', '2019-06-19 10:39:42');
INSERT INTO `sys_log` VALUES ('1141584042679353346', '1139097618239463425', 'admin1', '修改资源', 88, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-menu\",\"modifyTime\":1561009819735,\"parentId\":\"0\",\"perms\":\"devicemgr\",\"resourceId\":\"1140447865918201857\",\"resourceName\":\"设备中心\",\"type\":\"0\",\"url\":\"/deviceCenter\"}]', '192.168.50.69', '2019-06-20 13:50:20');
INSERT INTO `sys_log` VALUES ('1141584077752123394', '1139097618239463425', 'admin1', '修改资源', 84, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"icon\":\"el-icon-monitor\",\"modifyTime\":1561009828314,\"parentId\":\"1140447865918201857\",\"perms\":\"devicemgr:device\",\"resourceId\":\"1140525067779198978\",\"resourceName\":\"设备管理\",\"type\":\"1\",\"url\":\"/deviceCenter/device\"}]', '192.168.50.69', '2019-06-20 13:50:28');
INSERT INTO `sys_log` VALUES ('1141584111176531969', '1139097618239463425', 'admin1', '修改资源', 84, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561009836285,\"parentId\":\"1140525067779198978\",\"perms\":\"devicemgr:device:view\",\"resourceId\":\"1141156897918386178\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/log\"}]', '192.168.50.69', '2019-06-20 13:50:36');
INSERT INTO `sys_log` VALUES ('1141584254021943298', '1139097618239463425', 'admin1', '新增资源', 86, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1561009870341,\"icon\":\"\",\"parentId\":\"1140525067779198978\",\"perms\":\"devicemgr:device:save\",\"resourceId\":\"1141584253665427457\",\"resourceName\":\"用户管理\",\"type\":\"2\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-20 13:51:10');
INSERT INTO `sys_log` VALUES ('1141584368866181121', '1139097618239463425', 'admin1', '修改资源', 82, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561009897724,\"parentId\":\"1140525067779198978\",\"perms\":\"devicemgr:device:save\",\"resourceId\":\"1141584253665427457\",\"resourceName\":\"新建\",\"type\":\"2\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-20 13:51:38');
INSERT INTO `sys_log` VALUES ('1141584457919643650', '1139097618239463425', 'admin1', '新增资源', 59, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1561009918979,\"icon\":\"\",\"parentId\":\"1140525067779198978\",\"perms\":\"devicemgr:device:edit\",\"resourceId\":\"1141584457672179713\",\"resourceName\":\"编辑\",\"type\":\"2\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-20 13:51:59');
INSERT INTO `sys_log` VALUES ('1141584542246125570', '1139097618239463425', 'admin1', '新增资源', 83, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1561009939060,\"icon\":\"\",\"parentId\":\"1140525067779198978\",\"perms\":\"devicemgr:device:delete\",\"resourceId\":\"1141584541893804033\",\"resourceName\":\"删除\",\"type\":\"2\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-20 13:52:19');
INSERT INTO `sys_log` VALUES ('1141584609212383233', '1139097618239463425', 'admin1', '新增资源', 66, 'com.petrobest.pbmsapp.system.controller.ResourceController.save()', '[{\"createTime\":1561009955044,\"icon\":\"\",\"parentId\":\"1140525067779198978\",\"perms\":\"devicemgr:device:batchDelete\",\"resourceId\":\"1141584608935559169\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/user\"}]', '192.168.50.69', '2019-06-20 13:52:35');
INSERT INTO `sys_log` VALUES ('1141584662333243394', '1139097618239463425', 'admin1', '修改角色', 1233, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":0,\"modifyTime\":1561009966538,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1141151192029167617\",\"1139092311266304002\",\"1139094532343209986\",\"1139094593479385089\",\"1139094687633121282\",\"1139094762056851457\",\"1141151361114144769\",\"1139092372289232898\",\"1139094877593149442\",\"1139094933759074305\",\"1139094988742205442\",\"1139095068949880833\",\"1141151487954092033\",\"1139092439960133633\",\"1139096052665163777\",\"1139096152913223682\",\"1139096232609193986\",\"1139096304713474050\",\"1141151578412646402\",\"1139092496503545857\",\"1141151647048237057\",\"1140436352033628161\",\"1140436979409231874\",\"1141156833133166594\",\"1140447865918201857\",\"1140525067779198978\",\"1141156897918386178\",\"1141584253665427457\",\"1141584457672179713\",\"1141584541893804033\",\"1141584608935559169\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '192.168.50.69', '2019-06-20 13:52:48');
INSERT INTO `sys_log` VALUES ('1141931600163061761', '1139097618239463425', 'admin1', '修改角色', 36, 'com.petrobest.pbmsapp.system.controller.RoleController.update()', '[{\"defaultChecked\":0,\"modifyTime\":1561092684071,\"remark\":\"system admin\",\"resourceIds\":[\"1139092189140754434\",\"1139092256874569729\",\"1139093140731863042\",\"1139093272508506113\",\"1139093350195404801\",\"1139093416289247233\",\"1141151192029167617\",\"1139092311266304002\",\"1139094532343209986\",\"1139094593479385089\",\"1139094687633121282\",\"1139094762056851457\",\"1141151361114144769\",\"1139092372289232898\",\"1139094877593149442\",\"1139094933759074305\",\"1139094988742205442\",\"1139095068949880833\",\"1141151487954092033\",\"1139092439960133633\",\"1139096052665163777\",\"1139096152913223682\",\"1139096232609193986\",\"1139096304713474050\",\"1141151578412646402\",\"1139092496503545857\",\"1141151647048237057\",\"1140436352033628161\",\"1140436979409231874\",\"1141156833133166594\",\"1140447865918201857\",\"1140525067779198978\",\"1141156897918386178\",\"1141584253665427457\",\"1141584457672179713\",\"1141584541893804033\",\"1141584608935559169\"],\"roleId\":\"1139092591391285250\",\"roleName\":\"系统管理员\"}]', '127.0.0.1', '2019-06-21 12:51:24');
INSERT INTO `sys_log` VALUES ('1141982987773198338', '1139097618239463425', 'admin1', '修改用户', 15, 'com.petrobest.pbmsapp.system.controller.UserController.update()', '[{\"authCacheKey\":\"1141158822256672769\",\"createTime\":1560908440000,\"fullname\":\"测试\",\"lastLoginTime\":1560912058000,\"modifyTime\":1561104935867,\"password\":\"bd98d816b3b3dc61ff6686b642196b9d\",\"roleIds\":[\"1139099434368937985\"],\"status\":0,\"userId\":\"1141158822256672769\",\"username\":\"testuser\"}]', '127.0.0.1', '2019-06-21 16:15:36');
INSERT INTO `sys_log` VALUES ('1141983127628070913', '1139097618239463425', 'admin1', '修改用户', 16, 'com.petrobest.pbmsapp.system.controller.UserController.update()', '[{\"authCacheKey\":\"1141158822256672769\",\"createTime\":1560908440000,\"fullname\":\"测试\",\"lastLoginTime\":1560912058000,\"modifyTime\":1561104969304,\"password\":\"bd98d816b3b3dc61ff6686b642196b9d\",\"roleIds\":[\"1139099434368937985\"],\"status\":1,\"userId\":\"1141158822256672769\",\"username\":\"testuser\"}]', '127.0.0.1', '2019-06-21 16:16:09');
INSERT INTO `sys_log` VALUES ('1141988510719459330', '1139097618239463425', 'admin1', '修改用户', 0, 'com.petrobest.pbmsapp.system.controller.UserController.update()', '[{\"authCacheKey\":\"1141158822256672769\",\"createTime\":1560908440000,\"fullname\":\"测试\",\"lastLoginTime\":1560912058000,\"modifyTime\":1561106252733,\"password\":\"bd98d816b3b3dc61ff6686b642196b9d\",\"roleIds\":[\"1139099434368937985\",\"1139092591391285250\"],\"status\":1,\"userId\":\"1141158822256672769\",\"username\":\"testuser\"}]', '127.0.0.1', '2019-06-21 16:37:33');
INSERT INTO `sys_log` VALUES ('1141988595654115330', '1139097618239463425', 'admin1', '修改用户', 16, 'com.petrobest.pbmsapp.system.controller.UserController.update()', '[{\"authCacheKey\":\"1141158822256672769\",\"createTime\":1560908440000,\"fullname\":\"测试\",\"lastLoginTime\":1560912058000,\"modifyTime\":1561106272967,\"password\":\"bd98d816b3b3dc61ff6686b642196b9d\",\"roleIds\":[\"1139099434368937985\"],\"status\":1,\"userId\":\"1141158822256672769\",\"username\":\"testuser\"}]', '127.0.0.1', '2019-06-21 16:37:53');
INSERT INTO `sys_log` VALUES ('1141994150848655361', '1139097618239463425', 'admin1', '修改资源', 69, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107597258,\"orderNum\":1,\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:save\",\"resourceId\":\"1139093140731863042\",\"resourceName\":\"新建\",\"type\":\"2\",\"url\":\"/system/user/save\"}]', '192.168.50.69', '2019-06-21 16:59:57');
INSERT INTO `sys_log` VALUES ('1141994290607058946', '1139097618239463425', 'admin1', '修改资源', 64, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107630701,\"orderNum\":2,\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:edit\",\"resourceId\":\"1139093272508506113\",\"resourceName\":\"编辑\",\"type\":\"2\",\"url\":\"/system/user/edit\"}]', '192.168.50.69', '2019-06-21 17:00:31');
INSERT INTO `sys_log` VALUES ('1141994308504154113', '1139097618239463425', 'admin1', '修改资源', 96, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107634936,\"orderNum\":3,\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:delete\",\"resourceId\":\"1139093350195404801\",\"resourceName\":\"删除\",\"type\":\"2\",\"url\":\"/system/user/delete\"}]', '192.168.50.69', '2019-06-21 17:00:35');
INSERT INTO `sys_log` VALUES ('1141994322794147841', '1139097618239463425', 'admin1', '修改资源', 45, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107638392,\"orderNum\":4,\"parentId\":\"1139092256874569729\",\"perms\":\"system:user:batchDelete\",\"resourceId\":\"1139093416289247233\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/user/batchDelete\"}]', '192.168.50.69', '2019-06-21 17:00:38');
INSERT INTO `sys_log` VALUES ('1141994391584927746', '1139097618239463425', 'admin1', '修改资源', 64, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107654776,\"orderNum\":1,\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:save\",\"resourceId\":\"1139094532343209986\",\"resourceName\":\"新建\",\"type\":\"2\",\"url\":\"/system/role/save\"}]', '192.168.50.69', '2019-06-21 17:00:55');
INSERT INTO `sys_log` VALUES ('1141994403832295426', '1139097618239463425', 'admin1', '修改资源', 67, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107657695,\"orderNum\":2,\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:edit\",\"resourceId\":\"1139094593479385089\",\"resourceName\":\"编辑\",\"type\":\"2\",\"url\":\"/system/role/edit\"}]', '192.168.50.69', '2019-06-21 17:00:58');
INSERT INTO `sys_log` VALUES ('1141994415974805506', '1139097618239463425', 'admin1', '修改资源', 74, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107660580,\"orderNum\":3,\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:delete\",\"resourceId\":\"1139094687633121282\",\"resourceName\":\"删除\",\"type\":\"2\",\"url\":\"/system/role/delete\"}]', '192.168.50.69', '2019-06-21 17:01:01');
INSERT INTO `sys_log` VALUES ('1141994428574494721', '1139097618239463425', 'admin1', '修改资源', 68, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107663592,\"orderNum\":4,\"parentId\":\"1139092311266304002\",\"perms\":\"system:role:batchDelete\",\"resourceId\":\"1139094762056851457\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/role/batchDelete\"}]', '192.168.50.69', '2019-06-21 17:01:04');
INSERT INTO `sys_log` VALUES ('1141994661215760386', '1139097618239463425', 'admin1', '修改资源', 98, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107719028,\"orderNum\":1,\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:save\",\"resourceId\":\"1139094877593149442\",\"resourceName\":\"新建\",\"type\":\"2\",\"url\":\"/system/resource/save\"}]', '192.168.50.69', '2019-06-21 17:01:59');
INSERT INTO `sys_log` VALUES ('1141994674037747713', '1139097618239463425', 'admin1', '修改资源', 64, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107722119,\"orderNum\":2,\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:edit\",\"resourceId\":\"1139094933759074305\",\"resourceName\":\"编辑\",\"type\":\"2\",\"url\":\"/system/resource/edit\"}]', '192.168.50.69', '2019-06-21 17:02:02');
INSERT INTO `sys_log` VALUES ('1141994686863929345', '1139097618239463425', 'admin1', '修改资源', 77, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107725164,\"orderNum\":3,\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:delete\",\"resourceId\":\"1139094988742205442\",\"resourceName\":\"删除\",\"type\":\"2\",\"url\":\"/system/resource/delete\"}]', '192.168.50.69', '2019-06-21 17:02:05');
INSERT INTO `sys_log` VALUES ('1141994697840422914', '1139097618239463425', 'admin1', '修改资源', 61, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107727796,\"orderNum\":4,\"parentId\":\"1139092372289232898\",\"perms\":\"system:resource:batchDelete\",\"resourceId\":\"1139095068949880833\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/resource/batchDelete\"}]', '192.168.50.69', '2019-06-21 17:02:08');
INSERT INTO `sys_log` VALUES ('1141994732019806210', '1139097618239463425', 'admin1', '修改资源', 73, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107735933,\"orderNum\":1,\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:save\",\"resourceId\":\"1139096052665163777\",\"resourceName\":\"新建\",\"type\":\"2\",\"url\":\"/system/job/save\"}]', '192.168.50.69', '2019-06-21 17:02:16');
INSERT INTO `sys_log` VALUES ('1141994742811750402', '1139097618239463425', 'admin1', '修改资源', 62, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107738519,\"orderNum\":2,\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:edit\",\"resourceId\":\"1139096152913223682\",\"resourceName\":\"编辑\",\"type\":\"2\",\"url\":\"/system/job/edit\"}]', '192.168.50.69', '2019-06-21 17:02:19');
INSERT INTO `sys_log` VALUES ('1141994753859547138', '1139097618239463425', 'admin1', '修改资源', 62, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107741150,\"orderNum\":3,\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:delete\",\"resourceId\":\"1139096232609193986\",\"resourceName\":\"删除\",\"type\":\"2\",\"url\":\"/system/job/delete\"}]', '192.168.50.69', '2019-06-21 17:02:21');
INSERT INTO `sys_log` VALUES ('1141994765792342017', '1139097618239463425', 'admin1', '修改资源', 60, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561107743998,\"orderNum\":4,\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:batchDelete\",\"resourceId\":\"1139096304713474050\",\"resourceName\":\"批量删除\",\"type\":\"2\",\"url\":\"/system/job/batchDelete\"}]', '192.168.50.69', '2019-06-21 17:02:24');
INSERT INTO `sys_log` VALUES ('1141997187243806721', '1139097618239463425', 'admin1', '修改资源', 76, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561108321174,\"orderNum\":5,\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:view\",\"resourceId\":\"1141151578412646402\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/job\"}]', '192.168.50.69', '2019-06-21 17:12:01');
INSERT INTO `sys_log` VALUES ('1141997205396754434', '1139097618239463425', 'admin1', '修改资源', 92, 'com.petrobest.pbmsapp.system.controller.ResourceController.update()', '[{\"modifyTime\":1561108325612,\"orderNum\":0,\"parentId\":\"1139092439960133633\",\"perms\":\"system:job:view\",\"resourceId\":\"1141151578412646402\",\"resourceName\":\"查看\",\"type\":\"1\",\"url\":\"/system/job\"}]', '192.168.50.69', '2019-06-21 17:12:06');

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm`  (
  `perm_id` int(20) NOT NULL COMMENT '主键id',
  `resource` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作的资源',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权码',
  `handle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作方式',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`perm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `RESOURCE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源/按钮ID',
  `PARENT_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上级菜单ID',
  `RESOURCE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `URL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源URL',
  `PERMS` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限标识',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0目录 1菜单 2按钮',
  `ORDER_NUM` int(20) DEFAULT 0 COMMENT '排序',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`RESOURCE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1139092189140754434', '0', '系统管理', '/system', 'system', 'el-icon-s-platform', '0', 0, '2019-06-13 16:48:36', '2019-06-13 17:15:02');
INSERT INTO `sys_resource` VALUES ('1139092256874569729', '1139092189140754434', '用户管理', '/system/user', 'system:user', 'el-icon-user-solid', '1', 0, '2019-06-13 16:48:52', '2019-06-19 09:39:07');
INSERT INTO `sys_resource` VALUES ('1139092311266304002', '1139092189140754434', '角色管理', '/system/role', 'system:role', 'el-icon-user', '1', 0, '2019-06-13 16:49:05', '2019-06-19 09:39:11');
INSERT INTO `sys_resource` VALUES ('1139092372289232898', '1139092189140754434', '资源管理', '/system/resource', 'system:resource', 'el-icon-folder-opened', '1', 0, '2019-06-13 16:49:20', '2019-06-19 09:39:15');
INSERT INTO `sys_resource` VALUES ('1139092439960133633', '1139092189140754434', '定时任务', '/system/job', 'system:job', 'el-icon-tickets', '1', 0, '2019-06-13 16:49:36', '2019-06-19 09:39:19');
INSERT INTO `sys_resource` VALUES ('1139092496503545857', '1139092189140754434', '日志管理', '/system/log', 'system:log', 'el-icon-document-copy', '1', 0, '2019-06-13 16:49:49', '2019-06-19 09:39:23');
INSERT INTO `sys_resource` VALUES ('1139093140731863042', '1139092256874569729', '新建', '/system/user/save', 'system:user:save', NULL, '2', 1, '2019-06-13 16:52:23', '2019-06-21 16:59:57');
INSERT INTO `sys_resource` VALUES ('1139093272508506113', '1139092256874569729', '编辑', '/system/user/edit', 'system:user:edit', NULL, '2', 2, '2019-06-13 16:52:54', '2019-06-21 17:00:31');
INSERT INTO `sys_resource` VALUES ('1139093350195404801', '1139092256874569729', '删除', '/system/user/delete', 'system:user:delete', NULL, '2', 3, '2019-06-13 16:53:13', '2019-06-21 17:00:35');
INSERT INTO `sys_resource` VALUES ('1139093416289247233', '1139092256874569729', '批量删除', '/system/user/batchDelete', 'system:user:batchDelete', NULL, '2', 4, '2019-06-13 16:53:28', '2019-06-21 17:00:38');
INSERT INTO `sys_resource` VALUES ('1139094532343209986', '1139092311266304002', '新建', '/system/role/save', 'system:role:save', NULL, '2', 1, '2019-06-13 16:57:55', '2019-06-21 17:00:55');
INSERT INTO `sys_resource` VALUES ('1139094593479385089', '1139092311266304002', '编辑', '/system/role/edit', 'system:role:edit', NULL, '2', 2, '2019-06-13 16:58:09', '2019-06-21 17:00:58');
INSERT INTO `sys_resource` VALUES ('1139094687633121282', '1139092311266304002', '删除', '/system/role/delete', 'system:role:delete', NULL, '2', 3, '2019-06-13 16:58:32', '2019-06-21 17:01:01');
INSERT INTO `sys_resource` VALUES ('1139094762056851457', '1139092311266304002', '批量删除', '/system/role/batchDelete', 'system:role:batchDelete', NULL, '2', 4, '2019-06-13 16:58:49', '2019-06-21 17:01:04');
INSERT INTO `sys_resource` VALUES ('1139094877593149442', '1139092372289232898', '新建', '/system/resource/save', 'system:resource:save', NULL, '2', 1, '2019-06-13 16:59:17', '2019-06-21 17:01:59');
INSERT INTO `sys_resource` VALUES ('1139094933759074305', '1139092372289232898', '编辑', '/system/resource/edit', 'system:resource:edit', NULL, '2', 2, '2019-06-13 16:59:30', '2019-06-21 17:02:02');
INSERT INTO `sys_resource` VALUES ('1139094988742205442', '1139092372289232898', '删除', '/system/resource/delete', 'system:resource:delete', NULL, '2', 3, '2019-06-13 16:59:43', '2019-06-21 17:02:05');
INSERT INTO `sys_resource` VALUES ('1139095068949880833', '1139092372289232898', '批量删除', '/system/resource/batchDelete', 'system:resource:batchDelete', NULL, '2', 4, '2019-06-13 17:00:02', '2019-06-21 17:02:08');
INSERT INTO `sys_resource` VALUES ('1139096052665163777', '1139092439960133633', '新建', '/system/job/save', 'system:job:save', NULL, '2', 1, '2019-06-13 17:03:57', '2019-06-21 17:02:16');
INSERT INTO `sys_resource` VALUES ('1139096152913223682', '1139092439960133633', '编辑', '/system/job/edit', 'system:job:edit', NULL, '2', 2, '2019-06-13 17:04:21', '2019-06-21 17:02:19');
INSERT INTO `sys_resource` VALUES ('1139096232609193986', '1139092439960133633', '删除', '/system/job/delete', 'system:job:delete', NULL, '2', 3, '2019-06-13 17:04:40', '2019-06-21 17:02:21');
INSERT INTO `sys_resource` VALUES ('1139096304713474050', '1139092439960133633', '批量删除', '/system/job/batchDelete', 'system:job:batchDelete', NULL, '2', 4, '2019-06-13 17:04:57', '2019-06-21 17:02:24');
INSERT INTO `sys_resource` VALUES ('1140436352033628161', '0', '全网监控', '/monitorCenter', 'monitorCenter', 'el-icon-s-data', '0', 0, '2019-06-17 09:49:49', '2019-06-17 10:04:33');
INSERT INTO `sys_resource` VALUES ('1140436979409231874', '1140436352033628161', '地图监控', '/monitorCenter/map', 'monitorCenter:map', 'el-icon-data-analysis', '1', 0, '2019-06-17 09:52:19', '2019-06-19 09:38:55');
INSERT INTO `sys_resource` VALUES ('1140447865918201857', '0', '设备中心', '/deviceCenter', 'devicemgr', 'el-icon-menu', '0', 0, '2019-06-17 10:35:34', '2019-06-20 13:50:20');
INSERT INTO `sys_resource` VALUES ('1140525067779198978', '1140447865918201857', '设备管理', '/deviceCenter/device', 'devicemgr:device', 'el-icon-monitor', '1', 0, '2019-06-17 15:42:21', '2019-06-20 13:50:28');
INSERT INTO `sys_resource` VALUES ('1141151192029167617', '1139092256874569729', '查看', '/system/user', 'system:user:view', NULL, '1', 0, '2019-06-19 09:10:20', '2019-06-19 09:10:32');
INSERT INTO `sys_resource` VALUES ('1141151361114144769', '1139092311266304002', '查看', '/system/role', 'system:role:view', NULL, '1', 0, '2019-06-19 09:11:01', '2019-06-19 09:11:09');
INSERT INTO `sys_resource` VALUES ('1141151487954092033', '1139092372289232898', '查看', '/system/resource', 'system:resource:view', NULL, '1', 0, '2019-06-19 09:11:31', NULL);
INSERT INTO `sys_resource` VALUES ('1141151578412646402', '1139092439960133633', '查看', '/system/job', 'system:job:view', NULL, '1', 0, '2019-06-19 09:11:53', '2019-06-21 17:12:06');
INSERT INTO `sys_resource` VALUES ('1141151647048237057', '1139092496503545857', '查看', '/system/log', 'system:log:view', NULL, '1', 0, '2019-06-19 09:12:09', NULL);
INSERT INTO `sys_resource` VALUES ('1141156833133166594', '1140436979409231874', '查看', '/system/log', 'monitorCenter:map:view', NULL, '1', 0, '2019-06-19 09:32:45', NULL);
INSERT INTO `sys_resource` VALUES ('1141156897918386178', '1140525067779198978', '查看', '/system/log', 'devicemgr:device:view', NULL, '1', 0, '2019-06-19 09:33:01', '2019-06-20 13:50:36');
INSERT INTO `sys_resource` VALUES ('1141584253665427457', '1140525067779198978', '新建', '/system/user', 'devicemgr:device:save', NULL, '2', 0, '2019-06-20 13:51:10', '2019-06-20 13:51:38');
INSERT INTO `sys_resource` VALUES ('1141584457672179713', '1140525067779198978', '编辑', '/system/user', 'devicemgr:device:edit', NULL, '2', 0, '2019-06-20 13:51:59', NULL);
INSERT INTO `sys_resource` VALUES ('1141584541893804033', '1140525067779198978', '删除', '/system/user', 'devicemgr:device:delete', NULL, '2', 0, '2019-06-20 13:52:19', NULL);
INSERT INTO `sys_resource` VALUES ('1141584608935559169', '1140525067779198978', '批量删除', '/system/user', 'devicemgr:device:batchDelete', NULL, '2', 0, '2019-06-20 13:52:35', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ROLE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `DEFAULT_CHECKED` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '系统默认角色',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1139092591391285250', '系统管理员', 'system admin', '2019-06-13 16:50:12', '2019-06-21 12:51:24', '0');
INSERT INTO `sys_role` VALUES ('1139099434368937985', '普通用户', 'ordinary', '2019-06-13 17:17:23', '2019-06-19 10:39:42', '1');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `ROLE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `RESOURCE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`ROLE_ID`, `RESOURCE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139092189140754434');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139092256874569729');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139092311266304002');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139092372289232898');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139092439960133633');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139092496503545857');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139093140731863042');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139093272508506113');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139093350195404801');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139093416289247233');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139094532343209986');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139094593479385089');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139094687633121282');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139094762056851457');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139094877593149442');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139094933759074305');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139094988742205442');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139095068949880833');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139096052665163777');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139096152913223682');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139096232609193986');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1139096304713474050');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1140436352033628161');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1140436979409231874');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1140447865918201857');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1140525067779198978');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141151192029167617');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141151361114144769');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141151487954092033');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141151578412646402');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141151647048237057');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141156833133166594');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141156897918386178');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141584253665427457');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141584457672179713');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141584541893804033');
INSERT INTO `sys_role_resource` VALUES ('1139092591391285250', '1141584608935559169');
INSERT INTO `sys_role_resource` VALUES ('1139099434368937985', '1140436352033628161');
INSERT INTO `sys_role_resource` VALUES ('1139099434368937985', '1140436979409231874');
INSERT INTO `sys_role_resource` VALUES ('1139099434368937985', '1140447865918201857');
INSERT INTO `sys_role_resource` VALUES ('1139099434368937985', '1140525067779198978');
INSERT INTO `sys_role_resource` VALUES ('1139099434368937985', '1141156833133166594');
INSERT INTO `sys_role_resource` VALUES ('1139099434368937985', '1141156897918386178');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `USER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `FULLNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime(0) DEFAULT NULL COMMENT '最近访问时间',
  `SEX` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别 0男 1女',
  `THEME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主题',
  `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE INDEX `username`(`USERNAME`) USING BTREE COMMENT '唯一'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1139092661528436737', 'ansiyu', '675aaf0e6a0ef7f1f4e7e97d23395ca3', '管理员', NULL, NULL, NULL, '1', '2019-06-13 16:50:28', '2019-06-21 15:27:57', '2019-06-18 17:35:13', NULL, NULL, '/api/ava/20190621_17769875.jpg', NULL);
INSERT INTO `sys_user` VALUES ('1139097618239463425', 'admin1', '9b4c57854fecfd9cc56cb37207f33cf1', '管理员', NULL, NULL, NULL, '1', '2019-06-13 17:10:10', '2019-06-21 16:55:55', '2019-06-21 16:55:55', NULL, NULL, '/api/ava/20190621_37907783.jpg', NULL);
INSERT INTO `sys_user` VALUES ('1141158822256672769', 'testuser', 'bd98d816b3b3dc61ff6686b642196b9d', '测试', NULL, NULL, NULL, '1', '2019-06-19 09:40:40', '2019-06-21 16:37:53', '2019-06-19 10:40:58', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `USER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USER_ID`, `ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1139092661528436737', '1139092591391285250');
INSERT INTO `sys_user_role` VALUES ('1139097618239463425', '1139092591391285250');
INSERT INTO `sys_user_role` VALUES ('1141158822256672769', '1139099434368937985');

SET FOREIGN_KEY_CHECKS = 1;
