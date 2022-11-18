package com.petrobest.pbmsapp.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao extends BaseMapper<UserDO> {

    IPage<Map<String, Object>> listUserWithRole(Page page, @Param("userDO") UserDO userDO);

    IPage<UserDO> listUserWithRoleDO(Page page, @Param("userDO") UserDO userDO);
}
