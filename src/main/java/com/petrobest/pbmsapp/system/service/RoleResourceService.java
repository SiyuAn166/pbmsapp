package com.petrobest.pbmsapp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.system.domain.RoleResourceDO;

import java.util.List;

public interface RoleResourceService extends IService<RoleResourceDO> {

    List<String> listResourceIds(String roleId);

    boolean saveRoleResource(String roleId, List<String> resourceIds);
}
