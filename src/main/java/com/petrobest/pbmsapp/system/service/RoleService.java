package com.petrobest.pbmsapp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.system.domain.RoleDO;

import java.util.List;

public interface RoleService extends IService<RoleDO> {

    List<RoleDO> listRolesByUsername(String username);

    List<RoleDO> listRolesByUserId(String userId);

    List<String> listRolesRemarkByUsername(String username);
}
