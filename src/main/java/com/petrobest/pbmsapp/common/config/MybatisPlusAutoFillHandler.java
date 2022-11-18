package com.petrobest.pbmsapp.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisPlusAutoFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);//fieldName 对应实体类字段，非数据库字段
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyTime", new Date(), metaObject);
    }
}