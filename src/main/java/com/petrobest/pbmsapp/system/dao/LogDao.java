package com.petrobest.pbmsapp.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petrobest.pbmsapp.system.domain.LogDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogDao extends BaseMapper<LogDO> {
}
