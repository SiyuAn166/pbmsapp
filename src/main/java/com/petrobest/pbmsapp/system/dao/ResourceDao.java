package com.petrobest.pbmsapp.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petrobest.pbmsapp.system.domain.ResourceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceDao extends BaseMapper<ResourceDO> {
    List<ResourceDO> listByUserId(String userId);

    List<ResourceDO> listByUsername(String username);

    List<String> listPermsByUsername(String username);


}
