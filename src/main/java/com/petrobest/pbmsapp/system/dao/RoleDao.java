package com.petrobest.pbmsapp.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petrobest.pbmsapp.system.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleDao extends BaseMapper<RoleDO> {
    List<RoleDO> listRolesByUsername(String username);

    List<RoleDO> listRolesByUserId(String userId);

    List<String> listRolesRemarkByUsername(String username);
}
