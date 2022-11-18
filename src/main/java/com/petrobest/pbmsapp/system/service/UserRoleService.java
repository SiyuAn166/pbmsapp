package com.petrobest.pbmsapp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.system.domain.UserRoleDO;

import java.util.Collection;
import java.util.List;

public interface UserRoleService extends IService<UserRoleDO> {
    List<String> listRoleIds(String userId);
    boolean saveUserRole(String userId, List<String> roleIds);
}
